package com.vilin.spring.chapter04.bankapp.lookup.method.event;

import java.util.Properties;

public class DatabaseEventSender implements EventSender {
	@SuppressWarnings("unused")
	private Properties databaseProperties;
	
	public void setDatabaseProperties(Properties databaseProperties) {
		this.databaseProperties = databaseProperties;
	}
	
	@Override
	public void sendEvent(Event e) {
		// -- save events in the database
	}

}
