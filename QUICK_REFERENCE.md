# Quick Reference Guide

## 🚀 Quick Start Commands

### Local Testing (MERN App)

```bash
# Terminal 1: Backend
cd backend
npm install
npm start

# Terminal 2: Frontend  
cd frontend
npm install
npm start

# Terminal 3: Run Selenium Tests
cd selenium-tests
mvn clean test
```

### Docker Compose (Full Stack)

```bash
# Start all services
docker-compose up -d

# Run tests
docker-compose up selenium-tests

# View logs
docker-compose logs -f

# Stop all
docker-compose down
```

---

## 📋 GitHub Repository

**URL**: https://github.com/TahaBukhari-011/Dev-ops-assignment-4

**Key Files**:
- `Jenkinsfile` - CI/CD Pipeline
- `Dockerfile` - Selenium test image
- `docker-compose.yml` - Multi-container setup
- `setup-jenkins.sh` - Automated Jenkins setup
- `DEPLOYMENT_GUIDE.md` - Full documentation

---

## 🏗️ AWS EC2 Setup

```bash
# SSH into EC2
ssh -i your-key.pem ubuntu@your-ec2-ip

# Download and run setup script
wget https://raw.githubusercontent.com/TahaBukhari-011/Dev-ops-assignment-4/main/setup-jenkins.sh
chmod +x setup-jenkins.sh
./setup-jenkins.sh

# Get Jenkins admin password
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

# Access Jenkins
http://your-ec2-ip:8080
```

---

## ✅ Test Cases Summary

**Total**: 12 automated tests

| Category | Tests | Status |
|----------|-------|--------|
| Navigation | 4 | ✅ |
| Form Validation | 4 | ✅ |
| Error Handling | 3 | ✅ |
| Page Verification | 1 | ✅ |

---

## 🔧 Jenkins Pipeline Stages

```
1️⃣  Checkout → Clone from GitHub
2️⃣  Build Backend → npm install
3️⃣  Build Frontend → npm install
4️⃣  Start Services → Backend + Frontend running
5️⃣  Build Docker → Docker image creation
6️⃣  Test → Run Selenium tests
7️⃣  Generate Reports → Collect results
8️⃣  Cleanup → Stop services
```

---

## 📊 Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Backend | Node.js + Express | 18 |
| Frontend | React | 18 |
| Database | MongoDB | 5.0 |
| Tests | Selenium + TestNG | 4.15 + 7.8 |
| Build | Maven | 3.8+ |
| CI/CD | Jenkins | Latest |
| Container | Docker | Latest |
| Browser | Chrome | Headless |

---

## 🎯 Assignment Checklist

### Part I: Selenium Tests ✅
- [x] 10+ test cases (12 implemented)
- [x] Page Object Model
- [x] Headless Chrome
- [x] Maven build system
- [x] TestNG framework
- [x] WebDriver management

### Part II: Jenkins Pipeline ✅
- [x] GitHub repository
- [x] Dockerfile for tests
- [x] Jenkins pipeline stages
- [x] Docker integration
- [x] Automated test execution
- [x] AWS EC2 deployment
- [x] Containerized environment

---

## 🔗 File Locations

**Application Files**:
```
backend/
├── server.js
├── models/User.js
├── routes/authRoutes.js
├── middleware/authMiddleware.js
└── package.json

frontend/
├── src/
│   ├── components/ (SignIn, SignUp)
│   ├── pages/ (Home, Welcome)
│   └── services/authService.js
└── package.json
```

**Test Files**:
```
selenium-tests/
├── pom.xml
├── testng.xml
└── src/test/java/com/mernauth/
    ├── tests/MernAuthAppTest.java (12 tests)
    ├── pages/ (HomePage, SignInPage, SignUpPage, WelcomePage)
    └── utils/ (WebDriverFactory, BaseTest)
```

**DevOps Files**:
```
Root/
├── Dockerfile (Selenium)
├── Jenkinsfile (Pipeline)
├── docker-compose.yml
├── setup-jenkins.sh
├── DEPLOYMENT_GUIDE.md
├── ASSIGNMENT_SUMMARY.md
└── README.md
```

---

## 🧪 Test Execution Flow

```
1. Developer pushes code to GitHub
2. Jenkins webhook triggered
3. Jenkins pulls repository
4. Backend & Frontend built
5. Services started (5000, 3000)
6. Docker image built
7. Docker container started
8. Selenium tests execute in headless Chrome
9. Tests connect to running services
10. Reports generated
11. Services stopped
12. Pipeline completes
```

---

## 🐛 Troubleshooting Quick Fixes

### Tests Timeout
```bash
# Increase wait time in BaseTest.java
Duration.ofSeconds(10) → Duration.ofSeconds(20)
```

### Docker Issues
```bash
# Clear Docker cache
docker system prune -a

# Rebuild image
docker build -t mern-auth-tests:latest .
```

### Jenkins Issues
```bash
# View Jenkins logs
sudo tail -f /var/log/jenkins/jenkins.log

# Restart Jenkins
sudo systemctl restart jenkins
```

### MongoDB Connection
```bash
# Test connection
mongo "mongodb+srv://<user>:<pass>@<host>/mern-auth"

# Update .env with correct URI
MONGODB_URI=mongodb+srv://user:pass@host/mern-auth?retryWrites=true&w=majority
```

---

## 📈 Success Metrics

| Metric | Target | Status |
|--------|--------|--------|
| Test Cases | 10+ | ✅ 12 |
| Browser Coverage | Chrome | ✅ Headless |
| Pipeline Stages | Checkout + Test | ✅ 8 Stages |
| Containerization | Docker | ✅ Complete |
| GitHub Integration | Repository | ✅ Integrated |
| Jenkins Setup | AWS EC2 | ✅ Automated |
| Documentation | Complete | ✅ Comprehensive |

---

## 🎓 Key Learnings

1. **Selenium**: Automated testing with POM pattern
2. **Docker**: Containerizing Java, Node, React apps
3. **Jenkins**: CI/CD pipeline automation
4. **Maven**: Java build and dependency management
5. **TestNG**: Test framework and reporting
6. **AWS EC2**: Infrastructure setup and management
7. **GitHub**: Repository management and webhooks
8. **DevOps**: End-to-end automation workflow

---

## 📞 Support Resources

**Documentation**:
- `README.md` - Main documentation
- `DEPLOYMENT_GUIDE.md` - Detailed setup
- `ASSIGNMENT_SUMMARY.md` - Complete summary
- `selenium-tests/README.md` - Test documentation

**External Links**:
- [Selenium Documentation](https://www.selenium.dev)
- [Jenkins Pipeline](https://www.jenkins.io/doc/book/pipeline/)
- [Docker Docs](https://docs.docker.com/)
- [TestNG Guide](https://testng.org/)

---

## ✨ Assignment Status

**Overall**: ✅ **COMPLETE**

**Part I (Selenium)**: ✅ Complete
- 12 test cases implemented
- Page Object Model pattern
- Headless Chrome configured

**Part II (Jenkins)**: ✅ Complete  
- Jenkins pipeline created
- Docker integration complete
- AWS EC2 automation included

**Submission Ready**: ✅ YES

---

**Last Updated**: December 7, 2025
**Repository**: https://github.com/TahaBukhari-011/Dev-ops-assignment-4
**Status**: Ready for Deployment ✅
