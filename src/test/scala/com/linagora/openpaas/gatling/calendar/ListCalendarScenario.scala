package com.linagora.openpaas.gatling.calendar

import com.linagora.openpaas.gatling.Configuration._
import com.linagora.openpaas.gatling.calendar.CalendarsSteps._
import com.linagora.openpaas.gatling.core.DomainSteps.createGatlingTestDomainIfNotExist
import com.linagora.openpaas.gatling.core.UserSteps.getProfile
import com.linagora.openpaas.gatling.provisionning.ProvisioningSteps.provision
import com.linagora.openpaas.gatling.provisionning.RandomFeeder
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class ListCalendarScenario extends Simulation {
  val feeder = new RandomFeeder(UserCount)

  val scn = scenario("Testing OpenPaaS calendar listing")
    .exec(createGatlingTestDomainIfNotExist)
    .feed(feeder.asFeeder())
    .pause(1 second)
    .exec(provision())
    .pause(1 second)
    .exec(getProfile())
    .pause(1 second)
    .exec(createCalendar())
    .pause(1 second)
    .during(ScenarioDuration) {
      exec(listCalendarsForUser())
        .pause(1 second)
    }

  setUp(scn.inject(atOnceUsers(UserCount))).protocols(httpProtocol)
}