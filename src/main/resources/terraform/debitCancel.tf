provider "aws" {
  region = "us-east-1"
}

resource "aws_sqs_queue" "debit_cancel_queue" {
  name                        = "debit_cancel_queue.fifo"
  fifo_queue                  = true
  content_based_deduplication = true
}

resource "aws_apigatewayv2_api" "debit_api" {
  name          = "debit-api"
  protocol_type = "HTTP"
}


resource "aws_apigatewayv2_route" "auth_token" {
  api_id    = aws_apigatewayv2_api.debit_api.id
  route_key = "POST /auth/token"
  target    = "integrations/${aws_apigatewayv2_integration.auth_token.id}"
}


resource "aws_apigatewayv2_route" "debit_cancel" {
  api_id    = aws_apigatewayv2_api.debit_api.id
  route_key = "POST /debit-cancel"
  target    = "integrations/${aws_apigatewayv2_integration.debit_cancel.id}"
}


resource "aws_apigatewayv2_route" "swagger_ui" {
  api_id    = aws_apigatewayv2_api.debit_api.id
  route_key = "GET /swagger-ui/index.html"
  target    = "integrations/${aws_apigatewayv2_integration.swagger_ui.id}"
}


resource "aws_apigatewayv2_integration" "auth_token" {
  api_id                 = aws_apigatewayv2_api.debit_api.id
  integration_type       = "HTTP_PROXY"
  integration_method     = "POST"
  integration_uri        = "http://107.20.49.163:8080/auth/token"
  payload_format_version = "1.0"
}


resource "aws_apigatewayv2_integration" "debit_cancel" {
  api_id                 = aws_apigatewayv2_api.debit_api.id
  integration_type       = "HTTP_PROXY"
  integration_method     = "POST"
  integration_uri        = "http://107.20.49.163:8080/debit-cancel"
  payload_format_version = "1.0"
}


resource "aws_apigatewayv2_integration" "swagger_ui" {
  api_id                 = aws_apigatewayv2_api.debit_api.id
  integration_type       = "HTTP_PROXY"
  integration_method     = "GET"
  integration_uri        = "http://107.20.49.163:8080/swagger-ui/index.html"
  payload_format_version = "1.0"
}
