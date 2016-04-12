lib-Mail by ITQuasar
====================

[![Join the chat at https://gitter.im/ITQuasar/lib-mail](https://badges.gitter.im/ITQuasar/lib-mail.svg)](https://gitter.im/ITQuasar/lib-mail)
[![Travis CI](https://img.shields.io/travis/ITQuasar/lib-mail.svg)](https://travis-ci.org/ITQuasar/lib-mail)

![Tag](https://img.shields.io/github/tag/ITQuasar/lib-mail.svg)

[![Latest release](https://img.shields.io/github/release/ITQuasar/lib-mail.svg)](https://github.com/ITQuasar/lib-mail/releases)

[![Maven Central](https://img.shields.io/maven-central/v/com.itquasar.multiverse/lib-mail.svg)](http://search.maven.org/#search|gav|1|g%3A%22com.itquasar.multiverse%22%20AND%20a%3A%22lib-mail%22)

[![Open Issues](https://img.shields.io/github/issues/ITQuasar/lib-mail.svg)](https://github.com/ITQuasar/lib-mail/issues?q=is%3Aopen+is%3Aissue)
[![License](https://img.shields.io/github/license/ITQuasar/lib-mail.svg)](https://raw.githubusercontent.com/ITQuasar/lib-mail/master/LICENSE)


Wrapper around Java Mail API to make more easy work with email in Java, and Groovy and Scala too.


Usage
-----

To use, by now:

```
...
Message message = .... // javax.mail.Message
...
Email email = new Email(message);
```

To get recipients and subject use `getEnvelope()`. `Envelope` has methods `getTo()`, `getSubject()`, etc.

To get content use `getContent()`. `Content` has methods `getTextContent()`, `getHtmlContent()`, `getAttachments()`, etc.

The envelope and content parsing are made on demand (lazily). So, the headers are parsed when envelope getters are acessed, and the body when content getters are acessed. Just be sure to have an open mail session opened, as Java Mail API is lazy too.
