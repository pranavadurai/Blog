pipeline {
    agent any

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
                 sh 'scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@13.127.213.32:/home/ubuntu/deploy'
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.127.213.32 'cp /home/ubuntu/start.sh /home/ubuntu/deploy'"
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.127.213.32 'chmod 700 /home/ubuntu/deploy/start.sh'"
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.127.213.32 '/home/ubuntu/deploy/start.sh'"
            }
        }
    }
}
