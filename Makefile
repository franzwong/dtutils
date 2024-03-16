.PHONY: package
package:
	@echo "Building the native executable"
	@mvn clean package -Dnative

.PHONY: package_linux
package_linux:
	@echo "Building the native executable for Linux"
	@mvn clean package -Dnative -Dquarkus.native.container-build=true
