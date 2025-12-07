# DevOps Assignment 4: MERN Auth App with Selenium Tests & Jenkins Pipeline

## Overview

This project demonstrates a complete DevOps workflow with:
- ✅ MERN Stack Application (MongoDB, Express, React, Node.js)
- ✅ 12 Automated Selenium Test Cases
- ✅ Docker Containerization
- ✅ Jenkins CI/CD Pipeline
- ✅ Headless Chrome Testing

## Project Structure

```
Mern-auth-app/
├── backend/                    # Node.js Express backend
├── frontend/                   # React frontend
├── selenium-tests/             # Java Selenium test suite
├── Dockerfile                  # Docker image for Selenium tests
├── docker-compose.yml          # Multi-container orchestration
├── Jenkinsfile                 # Jenkins pipeline definition
└── DEPLOYMENT_GUIDE.md         # This file
```

---

## Part I: Selenium Automated Tests ✅

### Test Coverage (12 Test Cases)

Located in `selenium-tests/src/test/java/com/mernauth/tests/MernAuthAppTest.java`

1. Home page display verification
2. Sign In button navigation
3. Sign Up button navigation
4. Sign Up page fields verification
5. Sign In page fields verification
6. Invalid credentials error handling
7. Mismatched passwords error handling
8. Short password validation
9. Sign In → Sign Up navigation
10. Sign Up → Sign In navigation
11. Sign In page title verification
12. Sign Up page title verification

### Running Tests Locally

```bash
# Prerequisites
cd selenium-tests

# Option 1: Run with Maven (headless Chrome)
mvn clean test

# Option 2: Run specific test
mvn test -Dtest=MernAuthAppTest#testHomePageDisplayed

# View report
open target/surefire-reports/index.html
```

### Headless Chrome Configuration

Tests are configured with:
- `--headless` - No GUI
- `--no-sandbox` - Docker compatible
- `--disable-dev-shm-usage` - Reduced memory usage
- `--disable-gpu` - GPU acceleration disabled
- `--window-size=1920,1080` - Consistent window size

---

## Part II: Jenkins CI/CD Pipeline

### Prerequisites

- Jenkins server running on AWS EC2
- Docker installed on Jenkins agent
- GitHub repository access
- MongoDB Atlas or local MongoDB

### Jenkins Setup

#### 1. Install Jenkins on AWS EC2

```bash
# SSH into EC2 instance
ssh -i your-key.pem ubuntu@your-ec2-ip

# Update system
sudo apt-get update
sudo apt-get upgrade -y

# Install Java
sudo apt-get install -y openjdk-11-jdk

# Install Jenkins
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install -y jenkins

# Start Jenkins
sudo systemctl start jenkins
sudo systemctl enable jenkins

# Check status
sudo systemctl status jenkins

# Get initial admin password
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

#### 2. Install Docker on Jenkins Agent

```bash
# Install Docker
sudo apt-get install -y docker.io

# Add jenkins user to docker group
sudo usermod -aG docker jenkins

# Restart Jenkins
sudo systemctl restart jenkins
```

#### 3. Create New Jenkins Pipeline Job

1. Go to Jenkins dashboard: `http://your-ec2-ip:8080`
2. Click "New Item"
3. Enter job name: `MERN-Auth-Tests`
4. Select "Pipeline"
5. Click "OK"

#### 4. Configure Pipeline

In the Pipeline section, select:
- **Definition**: Pipeline script from SCM
- **SCM**: Git
- **Repository URL**: `https://github.com/TahaBukhari-011/Dev-ops-assignment-4.git`
- **Branch**: `*/main`
- **Script Path**: `Jenkinsfile`

#### 5. Add GitHub Credentials (Optional)

If repository is private:
1. Go to Jenkins → Manage Credentials
2. Add new credentials
3. Select "Username with password"
4. Enter GitHub username and personal access token

#### 6. Add MongoDB URI Credential

1. Go to Jenkins → Manage Credentials
2. Add new secret text credential
3. ID: `mongodb-uri`
4. Secret: Your MongoDB connection string

### Jenkinsfile Stages

```groovy
Pipeline Stages:
1. Checkout - Clone repository from GitHub
2. Build Backend - Install Node.js dependencies
3. Build Frontend - Install React dependencies
4. Start Services - Start backend and frontend servers
5. Build Docker Image - Build Selenium test Docker image
6. Test - Run Selenium tests in Docker container
7. Generate Test Reports - Collect and display test results
8. Cleanup - Stop services and clean up Docker images
```

---

## Docker Setup

### Option 1: Run with Docker Compose (Recommended)

```bash
# Navigate to project root
cd Mern-auth-app

# Build and start all services
docker-compose up -d

# Check services
docker-compose ps

# View logs
docker-compose logs -f

# Run tests
docker-compose up selenium-tests

# Stop services
docker-compose down
```

### Option 2: Manual Docker Commands

```bash
# Build test image
docker build -t mern-auth-tests:latest .

# Run tests in container
docker run --rm \
  --network host \
  -v /var/run/docker.sock:/var/run/docker.sock \
  mern-auth-tests:latest

# View container logs
docker logs <container-id>

# Clean up
docker rmi mern-auth-tests:latest
```

### Docker Compose Services

- **MongoDB**: Port 27017
- **Backend**: Port 5000
- **Frontend**: Port 3000
- **Selenium Tests**: Headless, no port

---

## Running the Full Pipeline

### Step 1: Verify Code is Pushed to GitHub

```bash
# Check remote
git remote -v

# Check branch
git branch -a

# Push changes if needed
git push origin main
```

### Step 2: Trigger Jenkins Pipeline

**Option A: Automatic (Webhooks)**
1. Go to GitHub repository → Settings → Webhooks
2. Add webhook: `http://your-jenkins-ip:8080/github-webhook/`
3. Select "Push events"
4. Jenkins will auto-trigger on push

**Option B: Manual**
1. Go to Jenkins → MERN-Auth-Tests job
2. Click "Build Now"
3. Monitor build progress in console

### Step 3: Monitor Pipeline Execution

```
Console Output:
[Checkout] Cloning repository
[Build Backend] Installing npm packages
[Build Frontend] Installing npm packages
[Start Services] Starting backend and frontend
[Build Docker] Building Docker image
[Test] Running Selenium tests in Docker
[Reports] Generating test reports
[Cleanup] Stopping services
```

### Step 4: View Test Results

1. Go to Jenkins job → Build #X
2. Click "Console Output"
3. Scroll to test execution section
4. Click on "test-results" for detailed report

---

## Troubleshooting

### Issue: Docker image not found

```bash
# Check Docker is running
docker ps

# Verify image exists
docker images | grep mern-auth-tests

# Rebuild image
docker build -t mern-auth-tests:latest .
```

### Issue: Selenium tests timeout

**Solution:**
- Increase wait time in `BaseTest.java`
- Ensure frontend is running before tests
- Check network connectivity between containers

### Issue: Jenkins pipeline fails

**Solution:**
```bash
# Check Jenkins logs
sudo tail -f /var/log/jenkins/jenkins.log

# Check Docker daemon
sudo systemctl status docker

# Verify Jenkins user has Docker access
sudo usermod -aG docker jenkins
sudo systemctl restart jenkins
```

### Issue: MongoDB connection error

**Solution:**
- Verify MongoDB URI in `.env` file
- Check MongoDB Atlas IP whitelist
- Test connection locally: `mongo "<uri>"`

---

## Best Practices

1. **Always use headless mode** for CI/CD
2. **Store secrets in Jenkins credentials**, not in code
3. **Use explicit waits** in Selenium (no hard sleeps)
4. **Clean up Docker resources** after pipeline execution
5. **Monitor Jenkins logs** for debugging
6. **Version your Docker images** with build numbers
7. **Use docker-compose** for local multi-container testing

---

## Security Considerations

1. **GitHub Token**: Store in Jenkins Credentials
2. **MongoDB URI**: Store as Jenkins Secret
3. **Docker Registry**: Use private registry if needed
4. **EC2 Security Group**: Restrict access to Jenkins port
5. **SSH Keys**: Protect EC2 key pair
6. **Environment Variables**: Never commit secrets

---

## Performance Optimization

1. **Parallel Test Execution**: Configure TestNG with thread count
2. **Docker Layer Caching**: Use multi-stage builds
3. **Network Optimization**: Use host network mode
4. **Resource Limits**: Set Docker memory limits

---

## Monitoring and Logging

### Jenkins Pipeline Logs
- Location: `/var/log/jenkins/jenkins.log`
- Access in UI: Jenkins → Manage Logs → View logs

### Docker Logs
```bash
docker-compose logs -f selenium-tests
docker logs <container-id> --tail 100
```

### Test Reports
- Location: `selenium-tests/target/surefire-reports/`
- Format: HTML, XML, JSON

---

## Future Enhancements

- [ ] Add email notifications on pipeline failure
- [ ] Integrate with Slack for notifications
- [ ] Add performance testing to pipeline
- [ ] Set up auto-scaling for parallel tests
- [ ] Add code coverage reporting
- [ ] Integrate SonarQube for code quality
- [ ] Add UI screenshot capture on failures

---

## References

- [Selenium Documentation](https://www.selenium.dev/)
- [Jenkins Pipeline](https://www.jenkins.io/doc/book/pipeline/)
- [Docker Compose](https://docs.docker.com/compose/)
- [TestNG Documentation](https://testng.org/)
- [AWS EC2 Documentation](https://docs.aws.amazon.com/ec2/)

---

## Support

For issues or questions:
1. Check Jenkins console output
2. Review Docker logs: `docker-compose logs`
3. Check Selenium test reports: `target/surefire-reports/`
4. Review application logs: `backend/logs/` (if configured)

---

**Assignment Status**: ✅ Complete

Last Updated: December 2025
