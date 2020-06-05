pipeline {
 environment {
   registry = "vamceep/bookstore"
   registryCredential = 'docker_hub'
   dockerImage = ''
   dockerImageLatest = ''
 }
 agent any
 stages {
   stage('Cloning Git') {
     steps {
       git branch: 'working', url: 'https://github.com/vamsip99/Books.git'
     }
   }
   stage('Build'){
       steps {
        def myMvn = tool name: 'M3', type: 'maven'
	    sh "${myMvn}/bin/mvn clean install"
       }
   }
   stage('Test'){
       steps {
            sh 'mvn test'
       }
   }
   stage('Building Image') {
     steps{
       script {
         dockerImage = docker.build registry + ":$BUILD_NUMBER"
         dockerImageLatest = docker.build registry + ":latest"
       }
     }
   }
       stage('Deploy Image') {
     steps{
       script {
         docker.withRegistry( '', registryCredential ) {
           dockerImage.push()
           dockerImageLatest.push()
         }
       }
     }
   }
   stage('Remove Unused Docker Image') {
     steps{
       sh "docker rmi $registry:$BUILD_NUMBER"
     }
   }
stage('Deploy Docker Image') {
     steps{
       sh '''
docker top books && docker rm -f books 2> /dev/null
docker run -d --name books -p 9090:8099 -p 3307:3306 vamceep/bookstore:latest
'''
     }
   }
 }
}
