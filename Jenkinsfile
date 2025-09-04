pipeline {
    agent any

    tools {
        maven 'Maven-3.9.11'   // Jenkins Maven installation name
    }

    environment {
        AWS_REGION   = "ap-south-1"                     // your AWS region
        ACCOUNT_ID   = "325910234813"              // replace with your AWS account ID
        ECR_REGISTRY = "${ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
        ECR_REPO     = "${ECR_REGISTRY}/java-app"
        IMAGE_TAG    = "1.0.0"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'colud', url: 'https://github.com/LucidKarthik/Java-app.git'
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
                sh 'docker build -t $ECR_REPO:$IMAGE_TAG .'
                sh 'docker tag $ECR_REPO:$IMAGE_TAG $ECR_REPO:latest'
            }
        }

        stage('Push to ECR') {
            steps {
                withAWS(credentials: 'AWS-Cred', region: "${AWS_REGION}") {
                    sh 'aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REGISTRY'
                    sh 'docker push $ECR_REPO:$IMAGE_TAG'
                    sh 'docker push $ECR_REPO:latest'
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

