## SMART HOME MANAGEMENT SYSTEM

# Overview

The Smart Home Management System is an extensive project utilizing the flexibility and extensible characteristics of the OSGi framework. The system consists of various packages tailored to different functionalities, aiming to provide seamless integration and control over different aspects of home automation.

# Package Descriptions:

- **Light Safe Package (Consumer)**: Facilitates control over lighting systems and manages security features.
- **Temp Ease Package (Consumer)**: Manages temperature settings and controls smart appliances.
- **Bright Touch Package (Consumer)**: Controls lighting and smart appliances.
- **Secure Comfort Package (Consumer)**: Integrates security and temperature control functionalities.
- **All In One Service Consumer**: Offers comprehensive control over multiple aspects of the home automation system.

# Producers:

1. **Light Control Service**: Controls home lighting.
2. **Thermistor Control Service**: Manages temperature control.
3. **Security System Service**: Handles security-related events and devices.
4. **Smart App Control Service**: Manages smart appliances.

# Scenario Explanation

The system follows a Producer-Consumer design pattern, where producers generate data or events, and consumers utilize this information to perform relevant actions.

# Installation and Setup

1. Right-click on the package containing AllInOneConsumer.
2. Select "Run As" -> "Run Configuration".
3. Create a new configuration under "OSGi Framework".
4. Select required components: LightControlProducer, SecuritySystemProvider, SmartAppControlProducer, ThermistorControlProducer, and AllInOneConsumer from the Workspace.
5. Run the configuration.

# Prerequisites

- Java JDK 8 or higher
- Eclipse IDE with Equinox
