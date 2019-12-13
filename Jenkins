pipeline {
     agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
     }
options {
        skipStagesAfterUnstable()
    }
    stages {

        stage('Build') {
            steps {
              sh './mvnw -DskipTests clean compile'
              
            }
        }

        stage('Test') {
            steps {
              sh  './mvnw test'
        
            }
        }

        stage('Deliver') {
            steps {
              sh './mvnw -DskipTests install'
           
            }

        post {
             always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
 
        }

    }
}
