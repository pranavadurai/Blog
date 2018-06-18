pipeline {
    agent any

    tools{
      maven 'localmaven'
 } 
    stages {
       
        pom = readMavenPom file: 'pom.xml'
        
       
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
                      sh 'scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@13.126.195.164:/home/ubuntu/deploy/${pom.version}'
                      sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.126.195.164 '/home/ubuntu/start.sh ${pom.version}'"
                 }  
            }
        }
    }

