def mainDir="myTemplate"

pipeline{
    agent any

    stages{
        stage('Git Pull'){
            steps{
                checkout scm
            }
        }
        stage('Build image'){
            steps{
                sh """
                ./gradlew clean build
                """
                script{
                    dockerImage = docker.build("choiseokwon/mytemplate:0.0.1")
                }
            }
        }
        stage('Push image'){
            steps{
                withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
                    script{
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy'){
            steps{
                sshagent(credentials : ["deploy-key"]) {
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@ec2-13-125-123-49.ap-northeast-2.compute.amazonaws.com"
                    withDockerRegistry([credentialsId: "dockerhub", url: ""]) {
                        script {
                            dockerImage.pull()
                            dockerImage.run('-d -p 8080:8080')
                        }
                    }
                }
            }
        }

    }
}