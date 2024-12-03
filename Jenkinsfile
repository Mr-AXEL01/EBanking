pipeline {
    agent any  // Use any available agent

    stages {
        stage('Declarative: Checkout SCM') {
            steps {
                checkout scm
            }
        }
    }

    post {
        always {
            node {  // This will now work as Jenkins uses the default agent
                archiveArtifacts artifacts: '**/*', allowEmptyArchive: true
            }
        }
    }
}
