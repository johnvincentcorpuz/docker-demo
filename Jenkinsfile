pipeline {
  agent any
  stages {
    stage('build ') {
      steps {
        nodejs('nodejs') {
          sh 'npm install'
        }

      }
    }
    stage('test') {
      steps {
        nodejs('nodejs') {
          sh 'npm test'
        }

      }
    }
  }
}