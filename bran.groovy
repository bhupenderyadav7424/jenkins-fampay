pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                // Checkout source code from repository
                git 'https://github.com/bhupenderyadav7424/bran.git'
                
                // Build Docker image
                sh 'docker build -t bran-image .'
            }
        }
        stage('Test') {
            steps {
                // Run tests
                sh 'docker run bran-image python manage.py test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy to Kubernetes cluster
                sh 'kubectl apply -f Kubernetes/bran-deployment.yaml'
            }
        }
    }
}