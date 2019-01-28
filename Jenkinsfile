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
          sh label: '', script: '''       printenv
                                          echo "Git commit: $GIT_COMMIT" 
                                          echo "Git branch: $GIT_BRANCH"
                                          echo "Git local branch: $GIT_LOCAL_BRANCH" 
                                          echo "Git previous commit:$GIT_PREVIOUS_COMMIT" 
                                          echo "Git previous successful commit: $GIT_PREVIOUS_SUCCESSFUL_COMMIT"
                                          echo "Git url: $GIT_URL"
                                          echo "Git url n: $GIT_URL_N"
                                          echo "Git author name: $GIT_AUTHOR_NAME"
                                          echo "Git commiter email: $GIT_COMMITTER_EMAIL"'''
         //def gitData=getCommitsRange()
         script {
            //def currentCommitHash = currentBuild.getAction(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String
            //def previousCommitHash = currentBuild.previousBuild(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String
            //print "currentCommitHash:${currentCommitHash}"
            //print "previousCommitHash:${previousCommitHash}"
            print "Project Name: ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}"
            def commitsRange = getCommitsRange(env.JOB_NAME, env.BUILD_NUMBER)
            print commitsRange
         }

      }
   }
   
  }
}

//Helper Methods
def getBuildInstanceFor(projectName, buildNumber) {
  return Jenkins.getInstance().getItemByFullName(projectName).getBuildByNumber(buildNumber)
}

def getCommitsRange(projectName, buildNumber) {
    def currentBuild = getBuildInstanceFor(projectName, buildNumber)
    def previousBuild = getBuildInstanceFor(projectName, buildNumber - 1)
    def currentCommitHash = currentBuild.getAction(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String
    def previousCommitHash = previousBuild.getAction(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String

    return [
        projectName: projectName,
        current: currentCommitHash,
        previous: previousCommitHash
    ]
} 

def getGitInfo() {
    def changeLogSets = currentBuild.changeSets



}
