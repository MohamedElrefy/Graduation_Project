pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'mohamedelrefy20'
        KUBERNETES_NAMESPACE = 'purely-app'
        HELM_RELEASE_NAME = 'graduation-release'
        ANSIBLE_PLAYBOOK_DIR = 'ansible'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Docker Images') {
            steps {
                script {
                    // Build Docker images for each service
                    sh '''
                        docker build -t ${DOCKER_REGISTRY}/cart-service:latest ./microservice-backend/cart-service
                        docker build -t ${DOCKER_REGISTRY}/order-service:latest ./microservice-backend/order-service
                        docker build -t ${DOCKER_REGISTRY}/product-service:latest ./microservice-backend/product-service
                        docker build -t ${DOCKER_REGISTRY}/frontend:latest ./frontend
                        docker build -t ${DOCKER_REGISTRY}/eureka-server:latest ./microservice-backend/service-registry
                        docker build -t ${DOCKER_REGISTRY}/user-service:latest ./microservice-backend/user-service
                        docker build -t ${DOCKER_REGISTRY}/notification-service:latest ./microservice-backend/notification-service
                        docker build -t ${DOCKER_REGISTRY}/category-service:latest ./microservice-backend/category-service
                        docker build -t ${DOCKER_REGISTRY}/auth-service:latest ./microservice-backend/auth-service
                        docker build -t ${DOCKER_REGISTRY}/api-gateway:latest ./microservice-backend/api-gateway 
                    '''
                    '''
                    '''
                }
            }
        }
        
        stage('Push Docker Images') {
            steps {
                script {
                    // Push Docker images to registry
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                            sh """
                            docker login -u ${USERNAME} -p ${PASSWORD}
                            docker push mohamedelrefy20/cart-service:latest
                            docker push mohamedelrefy20/order-service:latest
                            docker push mohamedelrefy20/product-service:latest
                            docker push mohamedelrefy20/frontend:latest
                            docker push mohamedelrefy20/eureka-server:latest
                            docker push mohamedelrefy20/notification-service:latest
                            docker push mohamedelrefy20/category-service:latest
                            docker push mohamedelrefy20/auth-service:latest
                            docker push mohamedelrefy20/api-gateway:latest
                            docker push mohamedelrefy20/user-service:latest
                            """ 
                       
                    
                }
            }
        }

        stage('Apply Ansible Playbook') {
            steps {
                script {
                    dir("${ANSIBLE_PLAYBOOK_DIR}") {
                        sh '''
                            ansible-playbook -i inventory playbook.yml
                        '''
                    }
                }
            }
        }
        
        
        
        stage('Deploy Updates to Kubernetes') {
            steps {
                script {
                    // Deploy using Helm
                    dir('Helm') {
                        sh '''
                            helm upgrade --install ${HELM_RELEASE_NAME} . \
                                --namespace ${KUBERNETES_NAMESPACE} \
                                
                        '''
                    }
                }
            }
        }
        
        
    }
    
    post {
        always {
            // Clean up workspace
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }