pipeline {
  agent {
    docker {
      image 'node6-alpine'
      args '-p 3000:30000'
    }

  }
  stages {
    stage('build ') {
      steps {
        sh 'npm install'
      }
    }
  }
}