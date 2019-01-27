node {
   def commit_id

   // def getBuildInstanceFor(projectName, buildNumber) {
   //  return Jenkins.getInstance().getItemByFullName(projectName).getBuildByNumber(buildNumber)
   // }

   // def getCommitsRange(projectName, buildNumber) {
   //  def currentBuild = getBuildInstanceFor(projectName, buildNumber)
   //  def previousBuild = getBuildInstanceFor(projectName, buildNumber - 1)
   //  def currentCommitHash = currentBuild.getAction(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String
   //  def previousCommitHash = previousBuild.getAction(hudson.plugins.git.util.BuildData.class).lastBuiltRevision.sha1String

   //  return [
   //      projectName: projectName,
   //      current: currentCommitHash,
   //      previous: previousCommitHash
   //  ]

   // }

   stage('Preparation') {
     checkout scm
     sh "git rev-parse --short HEAD > .git/commit-id"                        
     commit_id = readFile('.git/commit-id').trim()
   }
   stage('test') {
     nodejs(nodeJSInstallationName: 'nodejs') {
       sh 'npm install --only=dev'
       sh 'npm test'
     }

   }

   stage('notifier'){
        
        slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        echo "Git commit: $GIT_COMMIT" 
        echo "Git branch: $GIT_BRANCH"
        echo "Git local branch: $GIT_LOCAL_BRANCH" 
        echo "Git previous commit:$GIT_PREVIOUS_COMMIT" 
        echo "Git previous successful commit: $GIT_PREVIOUS_SUCCESSFUL_COMMIT"
        echo "Git url: $GIT_URL"
        echo "Git url n: $GIT_URL_N"
        echo "Git author name: $GIT_AUTHOR_NAME"
        echo "Git commiter email: $GIT_COMMITTER_EMAIL"

   }
   //stage('docker build/push') {
   //  docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
   //    def app = docker.build("wardviaene/docker-nodejs-demo:${commit_id}", '.').push()
   //  }
   //}
}
