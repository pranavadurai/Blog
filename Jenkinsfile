pipeline {
    agent any
    
    environment {
        VERSION = '0.0.0'
        deploy_Server = '13.126.125.4'
        docker_deploy_server = '13.127.41.218'
    }

    tools{
      maven 'localmaven'
 } 
    stages {
    
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }      
          
        stage('Pakage and archieve war file') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archividng...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }     
        
        stage('Deliver in shell Script') {
                 steps {
                      script {
                          def pom = readMavenPom file: 'pom.xml'
                          VERSION = pom.version
                          }
                      echo "${VERSION}"
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@${deploy_Server} 'mkdir -p /home/ubuntu/deploy/${VERSION}'"
                      sh "scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.war ubuntu@${deploy_Server}:/home/ubuntu/deploy/${VERSION}"	
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@${deploy_Server} '/home/ubuntu/stop.sh; /home/ubuntu/start.sh ${VERSION};'"
                 }  
            }
            
       stage('Build Docker Image and push') {
                 steps {
                      script {
                          def pom = readMavenPom file: 'pom.xml'
                          VERSION = pom.version
                          }
                       echo "${VERSION}"
                       sh "docker build -t pranavam21/blog:${VERSION} ."	
                 }  
                 post {
                     success {
                        script {
                          def pom = readMavenPom file: 'pom.xml'
                          VERSION = pom.version
                          }
                         echo "${VERSION}"
                         withDockerRegistry([ credentialsId: "Jenkindoc", url: "" ]) {	
                           sh "docker push pranavam21/blog:${VERSION}"
                          }
                     }

                 }

            }
            
        stage('Deploy the build docker in server') {
            steps {
                 script {
                          def pom = readMavenPom file: 'pom.xml'
                          VERSION = pom.version
                          }
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey root@${deploy_Server} 'docker pull pranavam21/blog:${VERSION}'"
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey root@${deploy_Server} 'docker run -d -t blog pranavam21/blog:${VERSION}'"
            }
        }                       
         }
    }

