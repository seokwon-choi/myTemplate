def mainDir="myTemplate"

pipeline{
    agent any

    environment {
        SLACK_TOKEN = credentials('slack-token')
    }

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
                    script {
                        withDockerRegistry([credentialsId: "dockerhub", url: ""]) {
                            sh """
                                ssh -o StrictHostKeyChecking=no ubuntu@ec2-13-125-123-49.ap-northeast-2.compute.amazonaws.com '
                                docker stop backend
                                docker rm backend
                                docker rmi choiseokwon/mytemplate:0.0.1
                                docker pull choiseokwon/mytemplate:0.0.1
                                docker run --name backend -d -p 8080:8080 choiseokwon/mytemplate:0.0.1'
                            """

                        }
                    }
                }
            }
        }

    }
    post {
        success {
            slackSend (color: '#36a64f', message: "배포 성공! ${env.BUILD_URL}", tokenCredentialId: ${env.SLACK_TOKEN})
        }
        failure {
            slackSend (color: '#FF0000', message: "배포 실패 ㅠ ${env.BUILD_URL}", tokenCredentialId: ${env.SLACK_TOKEN})
        }
    }
}