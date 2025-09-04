pipeline {
    agent any

    tools {
        maven 'Maven-3.9'   // The name you gave in Jenkins tools config
    }

    environment {
        IMAGE_NAME = "expleodockerkk/java-app"
        IMAGE_TAG  = "1.0.0"
        DOCKER_CREDENTIALS = credentials('Docker-Hub')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'dev', url: 'https://github.com/LucidKarthik/Java-app.git'
            }
        }

        stage('Clean Workspace') {
            steps {
                sh 'rm -rf target'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Tag Docker Image') {
            steps {
                sh 'docker tag $IMAGE_NAME:$IMAGE_TAG $IMAGE_NAME:latest'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Docker-Hub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push $IMAGE_NAME:$IMAGE_TAG'
                    sh 'docker push $IMAGE_NAME:latest'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}

