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
                 def pom = readMavenPom file: 'pom.xml'     
                 sh 'scp -v -o StrictHostKeyChecking=no  -i /var/lib/jenkins/secrets/mykey target/*.jar ubuntu@13.232.72.132:/home/ubuntu/deploy/${pom.version}'
                 sh "sshpass -p password ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/secrets/mykey ubuntu@13.232.72.132 '/home/ubuntu/start.sh ${pom.version}'"
            }
        }
    }
}
