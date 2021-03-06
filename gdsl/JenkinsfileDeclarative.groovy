pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/MadeInChina/PiggyMetrics.git'
        CREDENTIALS_ID = 'github-slam'
        ENVIRONMENT_ENDPOINT = 'http://192.168.2.34:8888/v2-beta/projects/1a5'
        ACCESS_KEY           = '602311D6074EC0DFCBB5'
        SECRET_KEY           = 'YttmUwex7ryzBLnM1CctyumSqac16AboL43Va8df'
        STACK_NAME           = 'Applications'
        SERVICE_NAME         = 'account-service'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: env.REPO_URL, credentialsId: env.CREDENTIALS_ID, branch: 'Finchley.BUILD-SNAPSHOT'
            }
        }
        stage('Build') {
            steps {
                dir ('account-service') {
                    sh 'mvn clean install'
                    def pom = readMavenPom file:'pom.xml'
                    print pom.version
                    env.version = pom.version
                }
            }
        }
        stage('Image Build And Push') {
            steps {
                dir ('account-service') {
                    script {
                        def app = docker.build 'registry.homelab.org/piggymetrics-account-service:2.3.0.RELEASE'
                    }
                }
            }
        }
        stage('Upgrade') {
            steps {
                dir ('account-service') {
                    sh "rancher-compose --url ${env.ENVIRONMENT_ENDPOINT}  --access-key ${env.ACCESS_KEY} --secret-key ${env.SECRET_KEY} -f ../docker-compose-on-rancher-ui.yml -env-file ../secrets -p ${env.STACK_NAME} up ${env.SERVICE_NAME} --force-upgrade -p -c -d"
                }
            }
        }

    }
}