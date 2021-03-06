def call()
{
    sh """
    cppcheck -q  --suppress="unusedFunction" --suppress="missingIncludeSystem" --suppress="unmatchedSuppression" --enable=all --template="{file},{line},{severity},{id},{message}" --project=build/compile_commands.json 2>&1 | tee cppcheck.txt
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
    failedTotalHigh: '0',
    unstableTotalNormal: '0',
    unHealthy: ''
}
