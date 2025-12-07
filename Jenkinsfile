pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_IMAGE = 'mern-auth-tests'
        DOCKER_TAG = "${BUILD_NUMBER}"
        BACKEND_URL = 'http://localhost:5000'
        FRONTEND_URL = 'http://localhost:3000'
        MONGODB_URI = credentials('mongodb-uri')
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Checking out code from GitHub =========='
                git branch: 'main', url: 'https://github.com/TahaBukhari-011/Dev-ops-assignment-4.git'
                echo '========== Code checkout successful =========='
            }
        }

        stage('Build Backend') {
            steps {
                echo '========== Building Backend =========='
                dir('backend') {
                    sh 'npm install'
                    echo '========== Backend dependencies installed =========='
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo '========== Building Frontend =========='
                dir('frontend') {
                    sh 'npm install'
                    echo '========== Frontend dependencies installed =========='
                }
            }
        }

        stage('Start Services') {
            steps {
                echo '========== Starting Backend and Frontend Services =========='
                sh '''
                    cd backend && npm start &
                    sleep 5
                    echo "Backend started"
                    cd ../frontend && npm start &
                    sleep 5
                    echo "Frontend started"
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                echo '========== Building Docker Image =========='
                script {
                    sh '''
                        docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                        docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest
                        echo "========== Docker image built successfully =========="
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                echo '========== Running Selenium Tests in Docker =========='
                script {
                    sh '''
                        docker run --rm \
                            --network host \
                            -v /var/run/docker.sock:/var/run/docker.sock \
                            ${DOCKER_IMAGE}:${DOCKER_TAG}
                        echo "========== Tests executed successfully =========="
                    '''
                }
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
                echo '========== Cleaning up =========='
                sh '''
                    pkill -f "npm start" || true
                    docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || true
                    echo "========== Cleanup completed =========="
                '''
            }
        }
    }

    post {
        always {
            echo '========== Pipeline execution completed =========='
            cleanWs()
        }
        success {
            echo '========== Pipeline successful =========='
            // Send success notification
            sh 'echo "All tests passed successfully!"'
        }
        failure {
            echo '========== Pipeline failed =========='
            // Send failure notification
            sh 'echo "Tests failed. Please check the logs."'
        }
    }
}
