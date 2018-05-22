def call(String status = 'success')
{
    tokens = "${env.JOB_NAME}".tokenize('/')
    org = tokens[tokens.size()-3]
    repo = tokens[tokens.size()-2]
    branch = tokens[tokens.size()-1]
    switch( status )
    {
        case "success":
        slackSend color: 'good', message: "${org}/${repo}/${branch} built successfully"
        break
        case "fail":
        slackSend color: 'bad', message: "${org}/${repo}/${branch} failed to build ${env.BUILD_URL}console"
    }
}
