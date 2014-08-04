job {
    name 'pull-request-builder'
    displayName 'Pull Request Builder'
    description 'This build compiles, runs unit tests and validates that the code for a pull request is suitable to merge in'
    label('maven-test-image')
    concurrentBuild()
    scm {
        checkoutRetryCount(3)
        git {
            remote {
                github('bryanstephens/spring-boot-car-finder')
                refspec('+refs/pull/*:refs/remotes/origin/pr/*')
                credentials('cb9fc9f8-d4f1-49c6-8099-ad4f99d7c102')
            }
            branch('${sha1}')
        }
    }
    triggers {
        pullRequest {
            admins('bryanstephens')
            userWhitelist('bryanstephens')
            orgWhitelist('infusionsoft')
            cron('H/5 * * * *')
            triggerPhrase('Ok to test')
//            onlyTriggerPhrase()
            useGitHubHooks()
            permitAll()
//            autoCloseFailedPullRequests()
        }
    }
}