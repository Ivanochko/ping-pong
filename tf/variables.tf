variable "access_key" {
        description = "Access key of AWS IAM User with the required permissions for SQS Queue creation and deletion"
}

variable "secret_key" {
        description = "Secret key of AWS IAM user with the required permissions for SQS Queue creation and deletion"
}

variable "region" {
        description = "Region for AWS"
        default = "us-east-1"
}

variable "sqs_name" {
        description = "Name of the sqs queue to be created. You can assign any unique name for the Queue"
        default = "ping_pong_sqs"
}