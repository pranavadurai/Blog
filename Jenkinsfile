pipeline {
    agent any
    
    environment {
        version = '0.0.0'
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
        
        stage('Deliver') {
                 steps {
                      script {
                          def pom = readMavenPom file: 'pom.xml'
                          version = pom.version
                      }

                      sh 'scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@13.126.195.164:/home/ubuntu/deploy/${version}'
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.126.195.164 '/home/ubuntu/start.sh ${version}'"
                 }  
            }
        }
    }

