#!/bin/bash

# Jenkins Setup Script for MERN Auth App with Selenium Tests
# Run this on your AWS EC2 instance to set up Jenkins

echo "========================================="
echo "Jenkins Setup for MERN Auth DevOps"
echo "========================================="

# Update system
echo "Updating system packages..."
sudo apt-get update
sudo apt-get upgrade -y

# Install Java
echo "Installing Java..."
sudo apt-get install -y openjdk-11-jdk

# Install Jenkins
echo "Installing Jenkins..."
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install -y jenkins

# Install Docker
echo "Installing Docker..."
sudo apt-get install -y docker.io

# Add jenkins user to docker group
echo "Configuring Jenkins Docker access..."
sudo usermod -aG docker jenkins

# Install Docker Compose
echo "Installing Docker Compose..."
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Start services
echo "Starting services..."
sudo systemctl start docker
sudo systemctl enable docker
sudo systemctl start jenkins
sudo systemctl enable jenkins

# Wait for Jenkins to start
echo "Waiting for Jenkins to start..."
sleep 30

# Get initial admin password
echo ""
echo "========================================="
echo "Jenkins Setup Complete!"
echo "========================================="
echo ""
echo "Jenkins Admin Password:"
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
echo ""
echo "Access Jenkins at:"
echo "http://$(hostname -I | awk '{print $1}'):8080"
echo ""
echo "Next steps:"
echo "1. Open Jenkins UI and complete setup"
echo "2. Install recommended plugins"
echo "3. Create new Pipeline job"
echo "4. Configure Pipeline from Git repository"
echo "5. Set GitHub repository URL:"
echo "   https://github.com/TahaBukhari-011/Dev-ops-assignment-4.git"
echo "6. Set script path: Jenkinsfile"
echo ""
