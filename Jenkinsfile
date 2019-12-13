pipeline {
     agent any
     
     
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
