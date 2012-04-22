/*----------------------------------------------------------------------------
 Project: Agile Tracking Tool
 Copyright 2008, 2009   Ben Schreur
 ------------------------------------------------------------------------------
 This file is part of Agile Tracking Tool.
 Agile Tracking Tool is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 Agile Tracking Tool is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with Agile Tracking Tool.  If not, see <http://www.gnu.org/licenses/>.
 ------------------------------------------------------------------------------*/
import org.agiletracking.*
import org.codehaus.groovy.grails.commons.*

class BootStrap {

	def authenticateService

	def init = { servletContext -> 
		DataSourceUtils.tuneDataSource(servletContext)

		def pmMd5Pass = authenticateService.passwordEncoder("pm")
		def pmAgile = new User(username:"pm",
				userRealName:"Mister Demo", passwd:pmMd5Pass,
				enabled:true,email:"agiletracking@gmail.com",
				emailShow:true,description:"None",
				agreeToTermsOfUse:true)

		def pmRole = new Role(description:"userRole", authority:"ROLE_PM")
		pmRole.addToPeople(pmAgile)
		pmRole.save()
		
		def adminMd5Pass = authenticateService.passwordEncoder("admin")
		def adminAgile = new User(username:"admin",
				userRealName:"Mister Demo", passwd:adminMd5Pass,
				enabled:true,email:"agiletracking@gmail.com",
				emailShow:true,description:"None",
				agreeToTermsOfUse:true)

		def adminRole = new Role(description:"userRole", authority:"ROLE_ADMIN")
		adminRole.addToPeople(adminAgile)
		adminRole.save()
		
		new Requestmap(url: "/project/**",
			configAttribute: "ROLE_PM,ROLE_ADMIN").save()

		new Requestmap(url: "/admin/**",
			configAttribute: "ROLE_ADMIN").save()
	}

	def destroy = {
	}
}
