pipeline {
    agent any

    stages {
        stage('Declarative: Checkout SCM') {
            steps {
                checkout scm
            }
        }
    }

    post {
        always {
            // Wrap your post-action inside the node block
            node {
                archiveArtifacts artifacts: '**/*', allowEmptyArchive: true
            }
        }
    }
}
