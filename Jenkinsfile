pipeline {
    agent any
    
    environment {
        version = '0.0.0'
        deploy_Server = '13.232.119.112'
    }

    tools{
      maven 'localmaven'
 } 
    stages {      
       
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.jar'
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
                      sh "scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@${deploy_Server}:/home/ubuntu/deploy/${VERSION}"	
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@${deploy_Server} '/home/ubuntu/stop.sh; /home/ubuntu/start.sh ${VERSION};'"
                 }  
            }
            
        stage('Deliver in docker') {
                 steps {
                      script {
                          def pom = readMavenPom file: 'pom.xml'
                          VERSION = pom.version
                          }
                      echo "${VERSION}"
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@${deploy_Server} 'mkdir -p /home/ubuntu/deploy/${VERSION}'"
                      sh "scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@${deploy_Server}:/home/ubuntu/deploy/${VERSION}"	
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@${deploy_Server} '/home/ubuntu/stop.sh; /home/ubuntu/start.sh ${VERSION};'"
                 }  
            }
        }
    }

