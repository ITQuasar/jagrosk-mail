lib-Mail by ITQuasar
====================

[![Join the chat at https://gitter.im/ITQuasar/lib-mail](https://badges.gitter.im/ITQuasar/lib-mail.svg)](https://img.shields.io/gitter/room/ITQuasar/lib-mail.svg)
![Travis CI](https://img.shields.io/travis/ITQuasar/lib-mail.svg)

![Tag](https://img.shields.io/github/tag/ITQuasar/lib-mail.svg)

![Latest release](https://img.shields.io/github/release/ITQuasar/lib-mail.svg)
![Latest release download](https://img.shields.io/github/downloads/ITQuasar/lib-mail/latest/total.svg)

![Maven Central](https://img.shields.io/maven-central/v/com.itquasar.multiverse/lib-mail.svg)

![Open Issues](https://img.shields.io/github/issues/ITQuasar/lib-mail.svg)
![License](https://img.shields.io/github/license/ITQuasar/lib-mail.svg)


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
