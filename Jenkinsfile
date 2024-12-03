pipeline {
    agent any

    tools {
        maven 'Maven-4.0.0'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
    }
}
