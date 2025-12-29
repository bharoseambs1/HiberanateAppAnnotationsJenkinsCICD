pipeline {
    agent any

    tools {
        maven 'Maven_3_9_11'
        jdk 'JDK17'
    }

    environment {
        TOMCAT_URL = 'http://localhost:8080'
        APP_NAME   = 'myapp'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                        url: 'https://github.com/bharoseambs1/HiberanateAppAnnotationsJenkinsCICD.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                withCredentials([usernamePassword(
                        credentialsId: 'tomcat-creds',
                        //usernameVariable: 'TOMCAT_USER',
                        //passwordVariable: 'TOMCAT_PASS'
                        usernameVariable: 'admin',
                        passwordVariable: 'admin123'
                )]) {
                    sh """
  curl -u $TOMCAT_USER:$TOMCAT_PASS \
  -T target/*.war \
  "$TOMCAT_URL/manager/text/deploy?path=/$APP_NAME&update=true"
  """
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful 🎉'
        }
        failure {
            echo 'Deployment failed ❌'
        }
    }
}