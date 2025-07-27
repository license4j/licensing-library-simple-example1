package com.example.simple.example1;

import com.license4j.License;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /**
         * Build license to use in any part of the application.
         */
        License.getInstance().getBuilder()
                /**
                 * Product hash code is displayed on the License Server products
                 * page.
                 */
                .product("8EF12B93899E09502BE7352098F8EB35")
                /**
                 * Default license file will be used, to use different location
                 * un-comment below.
                 */
                //.file(System.getProperty("user.home") + File.separator + "MyProduct" + File.separator + "license.l4j")
                /**
                 * On windows only, will be created at
                 * Computer\HKEY_CURRENT_USER\SOFTWARE\MyProduct key and license
                 * value.
                 */
                //.registry("MyProduct\\license")
                .build();

        /**
         * without any key given, validate method try to load license data from
         * license file or registry.
         *
         * This must be called on each application startup.
         */
        License.getInstance().validate();

        /**
         * If previously saved license is invalid, or there is no license on
         * disk or registry (first run), ask customer for the license key.
         */
        if (!License.getInstance().getStatus().isValid()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your license key: ");
            String key = scanner.nextLine();

            License.getInstance().validate(key);
        }

        printLicenseDetails();
    }

    private static void printLicenseDetails() {
        System.out.println("-------------------------------------------------");
        System.out.println("License Status          : " + License.getInstance().getStatus().isValid());
        System.out.println("License Status Error    : " + License.getInstance().getStatus().getCode());
        System.out.println("License Status Message  : " + License.getInstance().getStatus().getMessage());
        System.out.println();
        System.out.println("License Key             : " + License.getInstance().getLicenseInformation().getLicenseKey());
        System.out.println("License Type            : " + License.getInstance().getLicenseInformation().getLicenseType());
        System.out.println("License Expiration Date : " + License.getInstance().getLicenseInformation().getExpirationDateTime());
        System.out.println("License File Location   : " + License.getInstance().getLicenseInformation().getLicenseDataSaveLocation());
        System.out.println();
        System.out.println("Licensee Full Name      : " + License.getInstance().getLicenseeInformation().getFullName());
        System.out.println("Licensee E-Mail         : " + License.getInstance().getLicenseeInformation().getEMail());
        System.out.println("Licensee Company        : " + License.getInstance().getLicenseeInformation().getCompany());
        System.out.println();
        System.out.println("System User Name        : " + License.getInstance().getSystemInformation().getOSUserName());
        System.out.println("System Host Name        : " + License.getInstance().getSystemInformation().getOSHostName());
        System.out.println("System Domain Name      : " + License.getInstance().getSystemInformation().getDomainName());
        System.out.println("System OS Family        : " + License.getInstance().getSystemInformation().getOSFamily());
        System.out.println("System Java             : " + License.getInstance().getSystemInformation().getJava());
        System.out.println("System CPU Core Count   : " + License.getInstance().getSystemInformation().getCPUCoreCount());
        System.out.println("System is Virtual       : " + License.getInstance().getSystemInformation().isVirtual());
        System.out.println("System is Cloud         : " + License.getInstance().getSystemInformation().isCloud());
        System.out.println("System is Container     : " + License.getInstance().getSystemInformation().isContainer());
        System.out.println("-------------------------------------------------");
    }
}
