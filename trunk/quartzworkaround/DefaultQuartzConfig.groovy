//
//
//quartz {
//    autoStartup = true
//    jdbcStore = false
//}

//Workaround, change QuartzConfig.groovy as follows:

import grails.util.Environment

quartz {
	if (Environment.current != Environment.TEST) { autoStartup = true } else { autoStartup = false }
	jdbcStore = false
}
