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

        stage('Build Docker Test Image') {
            steps {
                echo '========== Building Docker Test Image =========='
                sh '''
                    docker build -f Dockerfile -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    echo "========== Docker test image built successfully =========="
                '''
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo '========== Running Selenium Tests in Docker =========='
                sh '''
                    docker run --rm \
                        -v /var/run/docker.sock:/var/run/docker.sock \
                        ${DOCKER_IMAGE}:${DOCKER_TAG}
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
                        ls -la selenium-tests/target/surefire-reports/
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
            emailext(
                subject: "✅ Jenkins Build #${BUILD_NUMBER} - MERN Auth Tests PASSED",
                body: '''
                    <h2>Build Successful!</h2>
                    <p><b>Project:</b> MERN Auth Application</p>
                    <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
                    <p><b>Build Status:</b> SUCCESS</p>
                    <p><b>Build URL:</b> ${BUILD_URL}</p>
                    <hr/>
                    <h3>Test Results:</h3>
                    <p>All Selenium tests have been executed successfully!</p>
                    <p><b>Console Output:</b></p>
                    <pre>${BUILD_LOG}</pre>
                ''',
                to: 'tahabukhari313@gmail.com',
                mimeType: 'text/html'
            )
        }
        failure {
            echo '========== Pipeline failed =========='
            emailext(
                subject: "❌ Jenkins Build #${BUILD_NUMBER} - MERN Auth Tests FAILED",
                body: '''
                    <h2>Build Failed!</h2>
                    <p><b>Project:</b> MERN Auth Application</p>
                    <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
                    <p><b>Build Status:</b> FAILURE</p>
                    <p><b>Build URL:</b> ${BUILD_URL}</p>
                    <hr/>
                    <p>Please check the console output for details.</p>
                    <p><b>Console Output:</b></p>
                    <pre>${BUILD_LOG}</pre>
                ''',
                to: 'tahabukhari313@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}
