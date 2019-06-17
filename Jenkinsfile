node{
    stage('Scm checkout'){
        git 'https://github.com/Zinkler88/SuperHeroApi.git'
    }


    stage('Mvn Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package"
    }
    stage('Build Docker Image'){
        sh 'docker build -t superhero-api .'
    }

    stage('Push Docker Image'){
        withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh 'docker login -u zakaria -p ${dockerHubPwd}'
        }
        sh 'docker push '
    }
}