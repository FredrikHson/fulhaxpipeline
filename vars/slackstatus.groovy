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
        case "failure":
        case "fail":
        slackSend color: '#ff0000', message: "${org}/${repo}/${branch} failed to build ${env.BUILD_URL}console"
        break
        case "facepalm":
        slackSend color: '#6c3000', message: "${org}/${repo}/${branch} facepalmed to hard ${env.BUILD_URL}"
        break
        case "unstable":
        slackSend color: '#6c3000', message: "${org}/${repo}/${branch} had an earthquake ${env.BUILD_URL}"
        break

    }
}
