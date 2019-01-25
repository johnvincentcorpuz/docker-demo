pipeline {
  agent any
  stages {
    stage('build ') {
      steps {
        nodejs(nodeJSInstallationName: 'nodejs', configId: 'nodeJSInstallationName') {
          sh 'npm install'
        }

      }
    }
    stage('test') {
      steps {
        nodejs(nodeJSInstallationName: 'nodejs', configId: 'nodeJSInstallationName') {
          sh 'npm test'
        }

      }
    }
  }
}