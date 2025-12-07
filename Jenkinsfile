pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_IMAGE = 'mern-auth-tests'
        DOCKER_TAG = "${BUILD_NUMBER}"
        BACKEND_URL = 'http://localhost:5000'
        FRONTEND_URL = 'http://localhost:3000'
        MONGODB_URI = 'mongodb://localhost:27017/mern-auth'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Checking out code from GitHub =========='
                git branch: 'main', url: 'https://github.com/TahaBukhari-011/Dev-ops-assignment-4.git'
                echo '========== Code checkout successful =========='
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo '========== Running Selenium Tests with Maven =========='
                sh '''
                    cd selenium-tests
                    mvn clean test
                    echo "========== Tests executed successfully =========="
                '''
            }
        }

        stage('Generate Test Reports') {
            steps {
                echo '========== Generating Test Reports =========='
                sh '''
                    if [ -d "selenium-tests/target/surefire-reports" ]; then
                        echo "Test reports found"
                        ls -la selenium-tests/target/surefire-reports/ || true
                    else
                        echo "No test reports directory found"
                    fi
                '''
            }
        }

        stage('Cleanup') {
            steps {
                echo '========== Cleanup completed =========='
            }
        }
    }

    post {
        always {
            echo '========== Pipeline execution completed =========='
        }
        success {
            echo '========== Pipeline successful =========='
        }
        failure {
            echo '========== Pipeline failed =========='
        }
    }
}
