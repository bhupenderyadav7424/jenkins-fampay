pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                // Checkout source code from repository
                git 'https://github.com/bhupenderyadav7424/hodr.git'
                
                // Build Docker image
                sh 'docker build -t hodor-image .'
            }
        }
        stage('Test') {
            steps {
                // Run tests
                sh 'docker run hodor-image go test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy to Kubernetes cluster
                sh 'kubectl apply -f Kubernetes/hodor-deployment.yaml'
            }
        }
    }
}