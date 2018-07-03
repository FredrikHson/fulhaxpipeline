def getChangeString()
{
    MAX_MSG_LEN = 100
    def changeString = ""

    echo "Gathering SCM changes"
    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++)
    {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++)
        {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)
            changeString += " - ${truncated_msg} [${entry.author}]\n"
        }
    }

    if (!changeString)
    {
        changeString = " - No new changes"
    }
    return changeString
}

def call(String status = 'success')
{
    tokens = "${env.JOB_NAME}".tokenize('/')
    org = tokens[tokens.size()-3]
    repo = tokens[tokens.size()-2]
    branch = tokens[tokens.size()-1]
    change = getChangeString()
    switch( status )
    {
        case "success":
        slackSend color: 'good', message: "${org}/${repo}/${branch} built successfully. ${change}"
        break
        case "failure":
        case "fail":
        slackSend color: '#ff0000', message: "${org}/${repo}/${branch} failed to build ${env.BUILD_URL}console ${change}"
        break
        case "facepalm":
        slackSend color: '#6c3000', message: "${org}/${repo}/${branch} facepalmed to hard ${env.BUILD_URL} ${change}"
        break
        case "unstable":
        slackSend color: '#6c3000', message: "${org}/${repo}/${branch} had an earthquake ${env.BUILD_URL} ${change}"
        break

    }
}
