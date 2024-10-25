pipeline {

    agent any
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'rm -rf camel_boot_rest; git clone https://github.com/cherepakhin/camel_boot_rest'
            }
        }

        stage('Unit tests') {
            steps {
                sh 'pwd;cd camel_boot_rest;./mvnw clean test'
            }
        }

        stage('Build bootJar') {
            steps {
                sh 'pwd;cd camel_boot_rest;./mvnw package'
            }
        }

        stage('Publish to Nexus') {
            environment {
                NEXUS_CRED = credentials('vasi')
            }
            steps {
                sh 'export NEXUS_CI_USER=admin; export NEXUS_CI_PASS=pass;echo $NEXUS_CI_USER;cd camel_boot_rest;ls;./mvnw deploy'
            }
        }
    }
}