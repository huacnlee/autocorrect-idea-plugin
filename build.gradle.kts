plugins {
    idea
    id("java")
    id("java-library")
    id("org.jetbrains.intellij") version "1.8.0"
}

group = "io.github.huacnlee"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-nop:1.7.36")
    implementation("io.github.huacnlee:autocorrect-java:2.3.2")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3.3")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("213")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set("""
        -----BEGIN CERTIFICATE-----
        MIIFTDCCAzQCCQDgR0mP2TUrpzANBgkqhkiG9w0BAQsFADBoMQswCQYDVQQGEwJD
        TjEQMA4GA1UECAwHU2ljaHVhbjEQMA4GA1UEBwwHQ2hlbmdkdTESMBAGA1UECgwJ
        SmFzb24gTGVlMSEwHwYJKoZIhvcNAQkBFhJodWFjbmxlZUBnbWFpbC5jb20wHhcN
        MjIxMDI0MTU1MTU4WhcNMjMxMDI0MTU1MTU4WjBoMQswCQYDVQQGEwJDTjEQMA4G
        A1UECAwHU2ljaHVhbjEQMA4GA1UEBwwHQ2hlbmdkdTESMBAGA1UECgwJSmFzb24g
        TGVlMSEwHwYJKoZIhvcNAQkBFhJodWFjbmxlZUBnbWFpbC5jb20wggIiMA0GCSqG
        SIb3DQEBAQUAA4ICDwAwggIKAoICAQCZEhVwVYwH1jjPi1Hs3Lu54s2sdMXqM46Q
        8jZ/s+J76Qj911eYwnhpn3MHTXc4x2K1CsD9gg3KDi/xtvcMu0RD0QPZ3Gc7JYDh
        vTxLkHjHgQXzM39WeTXFGpyxKVs1tlJl2MnR77FVK1Vsk9CofxHjbbYP2M3mR8VQ
        SShBc1Tunc8SyOsEkRsrsM7d4v/q6NdAmjESZcDbqBFnFmbIS0zXla3C0rUmuGcS
        luLT1BXngG7i0igTd5QIGbhWsj/cauqh6PCgFAAbn9k/OGa88b5DPg7LmvVGEdVr
        O8rjG1cRqu4mwkrW+c2CghxEgWblZcPTHTbntlQx+n/zytcDInWY0lfXYNi506Ae
        aWup42WX42nMKyRtUiKtbnqi+ccdHahAutaoNMFjEWtjreZovsqRruqgP3kzMsOT
        wy65t8YFeJxv2yDUD+BaKutIHr83gx6Ta26le25XGJhtBt9QadTWY3BCZa1rBhVH
        qGdARv9g4NVeuZa997l26BYFPAfG0HYrqWlWTi4Y9s/fpJhIpe5vi+yfxKwdIX+Q
        WqqzLRhv56jBeQnZD02+Gd272MKt9ncWbmkbQFGyVazMwD2rAzch4faLOpO731ce
        m02vFqOiXw3d8ns7EXi5F2r7sX3UOCDWTnMlGQ2aCkqLHZREDhMQt3J+BA0zyPvA
        c5vEVDVtZwIDAQABMA0GCSqGSIb3DQEBCwUAA4ICAQBzzXhUipYEEXjRxJt7nBq0
        x/8zFczsNP2AyqYQb3OJQNgZtDoYkP5R/Qd9HaDoaA5ivYGogsq/4WPKqC4Gn22w
        /Ha7iEGb1mxyvqZPApqRowECdvO81m3X7VeirhGdQL6KpjFj1I5gOCcwxcsGqzF/
        x3ZMF7I8HCWAGHq5M6FfhfX7wdxmGi7uPdxsmazHEB7ayDTnLFpdCWUURU13dAbQ
        bfZ5YJ1apS+8yw4KyCef8PgzcoY+k92ComfjrkhSZoHlAEiBsDqKJ7rLm7Glnmd6
        HDZ5PQMJPy18JloWF0KLBwNLweyYxb3CgCduCCBDV+0jJBz6SwKa6VJ97A1MS0Lx
        A4SYaA5rTW+wUwXPkV2tRzlug0fOTbbRJ6zwfIeIFPkUKQZrBHOv9YrIkC2BRAHr
        F9Gn/LT+oqPI8POLglqXkWEKZG3kHqKTEkJR/XvsFlPiyDQ2CKB0IM/pdk6Atmem
        DthioItrRwsRplOOngQ/0opW3jHGVl4MgkiPTwxI5UZ/IKRI33WbckzeZD2Ap4wm
        uP5E72xKXX2HgkK+fE+XLYPArPafb7S2VIs713Rr5ZbqbuA5ji6hR0nOtm+5/Wwb
        xrMsz7oLjQ2c0WGKP9QOoA4ccBlqLpd5UPAwAUoqhQQAmXHW/wm3l31HwWt+HS/v
        dNau9yxdsimcQsedLnkGSA==
        -----END CERTIFICATE-----
        """.trimIndent())

        privateKey.set("""
        -----BEGIN ENCRYPTED PRIVATE KEY-----
        MIIJnzBJBgkqhkiG9w0BBQ0wPDAbBgkqhkiG9w0BBQwwDgQIUW7QKhh27zoCAggA
        MB0GCWCGSAFlAwQBKgQQUX4DQavYnnIQ9ULiBW97iwSCCVAyi/8EdlgW1XB428rw
        lRgwM34b1sM8cjRmzC3YWW9lGVygxFBHEXudZhbSTfxdnIts6VehRNy4jFJmpgh/
        h1OxcrrL8v4/XNw0yL5nvmd8mMEQA/MsDEhaNbGbjoCrf+PZ/ZJLQIPxYIpCtUie
        65J5x+2KVSfdTuxk6PPK8aYVKyx3xhlQ76g7sKupts5yoeLNKIqYcX55aBH95xw8
        aB7xUMpwdVO2umRL7p+86n3vsUSD4ye6FEF72EwzxOtOZXKuDfR55SO/HCJL6U5t
        hNUEqDb98Kj7luBs1BzAySATqDnxDOho5u/ZnbC8K+C/YhQPexbHF7aEv4+61AFz
        PRH/14GJhfEri3mKThml1UBpI6Bisy9CFPijXcKYnWIRx3Hix8IpbYgHIM/HKpWL
        3+fOF8Wgtp8ZsHAQ8/FJP9sXKEFYvatk/LoZv6XJQC6En+28Vwq5+RoPzmNbW1SX
        xeenh/FyvNYMXwb2MAW473e2rAd9Gy2c/h2dmfUzA2imIDnv/v1OEimWG8JS+Wye
        z8twqn03aNSCGpPkehh3iW4aO1C61CcFUZFn0P4++nx9jtOEW5CpqIYwQfttmzrw
        YkZl9V4+E9lQnBAI6Xw8WB8VyOVbRkgJ5XJ+PnEJMj8lBjeQkcwR0yA1Md8vkmTR
        3nCEjIoRwbrTDetk8mLjpr5dd5OvR3rUruSW5Dnsq+ezSsqwSyGOTBti1zZLWTig
        w27GLs7VWcKmi/CNrn5YyEDx3zxHPeXzPO1EXGRe8Bi2b8QwCkpgwC/SADVi32Zj
        ukWmyMbwL2gbWbtorhV//JVw+DLzPq13aZHIawEjuEmKYBT/zWDmB34H6imaNN4N
        P1GFipJcRjpBQbBdy56awFUQ7vPiClQfWl/xAY2cvmbq7g8NDXdEfkgc70+gI3Bo
        vEM58Yo3ksVaWz8QVIQJC1hE3Tc98bNwkC8ll3gB1Vjq5vT2TFHWkmyt39mnNbJr
        h3bBFSEjgGnzNCFHoUgY7NotfxWUvu8Ht6EslbIFUohXNnoqGtn2579JQsxjRRif
        iG1XlnO/oEFZ/lT0GQ4qGPRQbcOtLTh+ohVDIdzLuhTCTTMlZS5ezO+UUH79LHft
        CLxojI09pviEbOt8pCCmahx2kyUUrBbpHnh0gji+bgFMKiSu/cVd4/yIwlObBADc
        tLxaVWcccWYFxiwqQo5vhTGcjxtM5GXKGiwQXNYjxx9J8Do8Z/IUhg2X6/snzERD
        3rPTncGrm7xGDPCpe1Ofqm56MwshMekHsrgYn80ID+5U0AKFi6dbYmhkT/P6WtmD
        Rfzy4ZE2ZGZRPmLV2G8iH48ZPJZ4fyFVDKgjk90pZLtsOf8S9AMHNzlYrCmcqO6f
        NBKV7OHDKsRPXrYsYg21YuBN5iej9P+UD2DMjOCBuQv11E71SEyuhVwFeoHLH5NW
        KcCa+5tjxYYlnVAoe4uRb8yHZRTdTvycmgymoR3VTieoojgwBgHQMbGVcdvIQ/da
        MbbkZ/Og4TzvfmhDmuaGl4191mw0uYdxxteZuOKwsaks0rP+OXfZlKO7+N7MEAxN
        vsXeC8zLD3dtPzuaJT0AB+2NWHXZi1wJGzMzTqYaYAfGq9xOw+HVPep5CumGcax7
        kYXDmgn+pELfqHVlI7NawuDZaZz7kDzxLCVtbkrjcaJ/QVwl+tlviz0RlKsJYcqF
        EpOITJsMVwrXSBJSJXZBVu2zWQHUrjVM3M/FV6enE3yeCDR1SRT9C24awr1aUycA
        QQl4HCMBN214w05Q81QcMeYvcD5m8Nf5V8/VqdlryY+KlzQDBFc5l8bmDrim+p2E
        75lN+C20ukQtIKOrWUc/0QX/GDXasBb4x51uvBYuM1+OlBFlK/KQ3ORa39nyqy8q
        1irhZl1dfJewgiKlkqBRJ6nahC82nrNgcNe77j22lC01oPBAqlhrJt4vjNiBGSXc
        RfNkkTUF/YAVyK9LgIwvgDg6Ek+KbzKU/Asc0EcQJ9zfoo8M+tdhLJJtDABERCyo
        QLdK4LZdVIDbAJ85eaQq4B8dFwjCQB9nXW5roMt+GoxqAsiHHyksXg7+vYxusmor
        epVQfvzNq9DsqRoEL/iCEMeB3DR8x11xlhOyIgRgU+VycfSTQtxUTmpyc9l0gMNL
        vDdxMrw631iEwCicT7+dbphBGYswdRhSPwut04ToSlzmNbnalCMyHpb6OmAZW1gu
        ervsIX1+A90VZrRbHZSCocErJp+Utauea7w3NLTr1ttzVo4iNIvk1jKDL1+Bt5Vr
        isYTDZGC+vwYDll/bTzFXDwg4nSOWS6UuREmYJww4O215lN2HDazRgRFqLqAWMTT
        X6oMimZUM2Lq/u4mO7Y8xOrF0YX33ldoEGrtxGNUehOe4x1/q+IlkvbH8s25dkFp
        +0UX9hWxmfhXUPt7Di33NdnM73POUON8BSQxKtme3vDejVzDtYxXchjWWPbaoDID
        hcycofaIAfsUfw1fiXDLnlLMGg2sCxEnrHWhKjE8FCVxYhxoyWVGusPxRK2bgdLS
        1zQKT+xd4L73EN/6m5OyhAOI7UBUxF39cLlUObtGr/TQh7qj0NvzR8UML+W1Ca7y
        UMHsCu/P3kAXchyG/zNxGNtvKiPedvgkNcr02m5HcQpjQGczAr7GNHM3A4RtExRb
        4vaDCdb1+gzBh7GF+DidH9OOfabIO1SN5X8b4Owlai8yeoit3PciafCIobtgW6xt
        xty8pBrU4Gku485EEP0Nu3ZN1FVteNVLMVixrjhJnWSkD7OjJcPIQ2Tt2KKazbAU
        ZFH48Mfy8pdZOgEUNMkfdJiMMRQzto5774h8C2aQGsFaDYrUL8xslSt7WMo88F60
        /0R+toyI0/dEoM6eM5n9OmWyfeJV1AgCy3ETKLQK3L8yzvFikQ71re37hF8Id14a
        CPgEI8zT54SAhe0A7EHYBF0pAmh1fccWOq4cOj2sCMyLDFjck8OyPZXpp9Tmyahz
        n79cYDaE5Mwi2FX2U5kgpD0Jk4dgJmRU3h0E3F8O+3r4O9hDM8nIGq4uj82tzK3Y
        esh19v53WdAGDiWAYC5XgUPDKh+rEIpEoF4+uDfCpfbJn/Ehkrwdch/yNbgCwp1e
        YmT5+rLr/xkAVjmq/Yn+svMyF19TxQLO01uw5xWeRxPNMr3KZvnorWGDeEf57+0X
        gTjR7nAw5NDYiG90v28Iy7kdhg==
        -----END ENCRYPTED PRIVATE KEY-----
        """.trimIndent())

        password.set(System.getenv("IDEA_PUBLISH_PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("IDEA_PUBLISH_TOKEN"))
    }
}
