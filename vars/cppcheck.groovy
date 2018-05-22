def call(String folders = '.')
{
    sh """
    cppcheck -q --enable=all --template="{file},{line},{severity},{id},{message}" ${folders} 2>&1 | tee cppcheck.txt
    """
    warnings canComputeNew: false,
    canResolveRelativePaths: false,
    categoriesPattern: '',
    defaultEncoding: '',
    excludePattern: '',
    healthy: '',
    includePattern: '',
    messagesPattern: '',
    parserConfigurations: [[parserName: 'cppcheck',
    pattern: 'cppcheck.txt']],
    unHealthy: ''
}
