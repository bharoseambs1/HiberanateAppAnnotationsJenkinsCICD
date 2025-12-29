pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        TOMCAT_URL = 'http://localhost:8080'
        APP_NAME   = 'hibernateAppAnnotations'
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
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'tomcat-creds',
                    usernameVariable: 'TOMCAT_USER',
                    passwordVariable: 'TOMCAT_PASS'
                )]) {
                    bat '''
                    echo Finding WAR file...
                    for %%f in (target\\*.war) do set WAR_FILE=%%f

                    echo WAR file is %WAR_FILE%

                    curl -u %TOMCAT_USER%:%TOMCAT_PASS% ^
                    -T %WAR_FILE% ^
                    "http://localhost:8080/manager/text/deploy?path=/hibernateAppAnnotations&update=true"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful'
        }
        failure {
            echo 'Deployment failed'
        }
    }
}
