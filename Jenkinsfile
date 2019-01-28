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
    stage('notifier'){
      steps {
          slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
          sh label: '', script: '''       echo "Git commit: $GIT_COMMIT" 
                                          echo "Git branch: $GIT_BRANCH"
                                          echo "Git local branch: $GIT_LOCAL_BRANCH" 
                                          echo "Git previous commit:$GIT_PREVIOUS_COMMIT" 
                                          echo "Git previous successful commit: $GIT_PREVIOUS_SUCCESSFUL_COMMIT"
                                          echo "Git url: $GIT_URL"
                                          echo "Git url n: $GIT_URL_N"
                                          echo "Git author name: $GIT_AUTHOR_NAME"
                                          echo "Git commiter email: $GIT_COMMITTER_EMAIL"'''
      }
    }
   
  }
}