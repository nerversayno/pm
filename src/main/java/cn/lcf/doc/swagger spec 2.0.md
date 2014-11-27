
<a id="user-content-swagger-restful-api-documentation-specification" class="anchor" href="#swagger-restful-api-documentation-specification" aria-hidden="true"><span class="octicon octicon-link"></span></a>Swagger RESTful API Documentation Specification</h1>

<h4>
<a id="user-content-version-20" class="anchor" href="#version-20" aria-hidden="true"><span class="octicon octicon-link"></span></a>Version 2.0</h4>

<p>The key words "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL NOT", "SHOULD", "SHOULD NOT", "RECOMMENDED", "MAY", and "OPTIONAL" in this document are to be interpreted as described in <a href="http://www.ietf.org/rfc/rfc2119.txt">RFC 2119</a>.</p>

<p>The Swagger specification is licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0.html">The Apache License, Version 2.0</a>.</p>

<h2>
<a id="user-content-introduction" class="anchor" href="#introduction" aria-hidden="true"><span class="octicon octicon-link"></span></a>Introduction</h2>

<p>Swagger™  is a project used to describe and document RESTful APIs.</p>

<p>The Swagger specification defines a set of files required to describe such an API. These files can then be used by the Swagger-UI project to display the API and Swagger-Codegen to generate clients in various languages. Additional utilities can also take advantage of the resulting files, such as testing tools.</p>

<h2>
<a id="user-content-revision-history" class="anchor" href="#revision-history" aria-hidden="true"><span class="octicon octicon-link"></span></a>Revision History</h2>

<table>
<thead>
<tr>
<th>Version</th>
<th>Date</th>
<th>Notes</th>
</tr>
</thead>
<tbody>
<tr>
<td>2.0</td>
<td>2014-09-08</td>
<td>Release of Swagger 2.0</td>
</tr>
<tr>
<td>1.2</td>
<td>2014-03-14</td>
<td>Initial release of the formal document.</td>
</tr>
<tr>
<td>1.1</td>
<td>2012-08-22</td>
<td>Release of Swagger 1.1</td>
</tr>
<tr>
<td>1.0</td>
<td>2011-08-10</td>
<td>First release of the Swagger Specification</td>
</tr>
</tbody>
</table>

<h2>
<a id="user-content-definitions" class="anchor" href="#definitions" aria-hidden="true"><span class="octicon octicon-link"></span></a>Definitions</h2>

<h5>
<a id="user-content-path-templating" class="anchor" href="#path-templating" aria-hidden="true"><span class="octicon octicon-link"></span></a>
<a name="user-content-pathTemplating"></a>Path Templating</h5>

<p>Path templating refers to the usage of curly braces ({}) to mark a section of a URL path as replaceable using path parameters.</p>

<h5>
<a id="user-content-mime-types" class="anchor" href="#mime-types" aria-hidden="true"><span class="octicon octicon-link"></span></a>
<a name="user-content-mimeTypes"></a>Mime Types</h5>

<p>Mime type definitions are spread across several resources. The mime type definitions should be in compliance with <a href="http://tools.ietf.org/html/rfc6838">RFC 6838</a>.</p>

<p>Some examples of possible mime type definitions:</p>

<pre><code>  text/plain; charset=utf-8
  application/json
  application/vnd.github+json
  application/vnd.github.v3+json
  application/vnd.github.v3.raw+json
  application/vnd.github.v3.text+json
  application/vnd.github.v3.html+json
  application/vnd.github.v3.full+json
  application/vnd.github.v3.diff
  application/vnd.github.v3.patch
</code></pre>

<h5>
<a id="user-content-http-status-codes" class="anchor" href="#http-status-codes" aria-hidden="true"><span class="octicon octicon-link"></span></a>
<a name="user-content-httpCodes"></a>HTTP Status Codes</h5>

<p>The HTTP Status Codes are used to indicate the status of the executed operation. The available status codes are described by <a href="http://tools.ietf.org/html/rfc7231#section-6">RFC 7231</a> and in the <a href="http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml">IANA Status Code Registry</a>.</p>

<h2>
<a id="user-content-specification" class="anchor" href="#specification" aria-hidden="true"><span class="octicon octicon-link"></span></a>Specification</h2>

<h3>
<a id="user-content-format" class="anchor" href="#format" aria-hidden="true"><span class="octicon octicon-link"></span></a>Format</h3>

<p>The files describing the RESTful API in accordance with the Swagger specification are represented as JSON objects and conform to the JSON standards.</p>

<p>For example, if a field is said to have an array value, the JSON array representation will be used:</p>

<div class="highlight highlight-js"><pre>{
   <span class="pl-s1"><span class="pl-pds">"</span>field<span class="pl-pds">"</span></span> <span class="pl-k">:</span> [...]
}</pre></div>

<p>While the API is described using JSON it does not impose a JSON input/output to the API itself.</p>

<p>All field names in the specification are <strong>case sensitive</strong>.</p>

<p>The schema exposes two types of fields. Fixed fields, which have a declared name, and Patterned fields, which declare a regex pattern for the field name. Patterned fields can have multiple occurrences as long as each has a unique name. </p>

<h3>
<a id="user-content-file-structure" class="anchor" href="#file-structure" aria-hidden="true"><span class="octicon octicon-link"></span></a>File Structure</h3>

<p>The Swagger representation of the API is made of a single file. However, parts of the definitions can be split into separate files, at the discretion of the user. This is applicable for <code>$ref</code> fields in the specification as follows from the <a href="http://json-schema.org">JSON Schema</a> definitions.</p>

<p>By convention, the Swagger specification file is named <code>swagger.json</code>.</p>

<h3>
<a id="user-content-data-types" class="anchor" href="#data-types" aria-hidden="true"><span class="octicon octicon-link"></span></a>Data Types</h3>

<p>Primitive data types in the Swagger Specification are based on the types supported by the <a href="http://json-schema.org/latest/json-schema-core.html#anchor8">JSON-Schema Draft 4</a>. Models are described using the <a href="#schemaObject">Schema Object</a> which is a subset of JSON Schema Draft 4.</p>

<p>An additional primitive data type <code>"file"</code> is used by the <a href="#parameterObject">Parameter Object</a> and the <a href="#responseObject">Response Object</a> to set the parameter type or the response as being a file.</p>

<p><a name="user-content-dataTypeFormat"></a>Primitives have an optional modifier property <code>format</code>. Swagger uses several known formats to more finely define the data type being used. However, the <code>format</code> property is an open <code>string</code>-valued property, and can have any value to support documentation needs. Formats such as <code>"email"</code>, <code>"uuid"</code>, etc., can be used even though they are not defined by this specification. The formats defined by the Swagger Specification are:</p>

<table>
<thead>
<tr>
<th>Common Name</th>
<th><a href="#dataTypeType"><code>type</code></a></th>
<th><a href="#dataTypeFormat"><code>format</code></a></th>
<th>Comments</th>
</tr>
</thead>
<tbody>
<tr>
<td>integer</td>
<td><code>integer</code></td>
<td><code>int32</code></td>
<td>signed 32 bits</td>
</tr>
<tr>
<td>long</td>
<td><code>integer</code></td>
<td><code>int64</code></td>
<td>signed 64 bits</td>
</tr>
<tr>
<td>float</td>
<td><code>number</code></td>
<td><code>float</code></td>
<td></td>
</tr>
<tr>
<td>double</td>
<td><code>number</code></td>
<td><code>double</code></td>
<td></td>
</tr>
<tr>
<td>string</td>
<td><code>string</code></td>
<td></td>
<td></td>
</tr>
<tr>
<td>byte</td>
<td><code>string</code></td>
<td><code>byte</code></td>
<td></td>
</tr>
<tr>
<td>boolean</td>
<td><code>boolean</code></td>
<td></td>
<td></td>
</tr>
<tr>
<td>date</td>
<td><code>string</code></td>
<td><code>date</code></td>
<td>As defined by <code>full-date</code> - <a href="http://xml2rfc.ietf.org/public/rfc/html/rfc3339.html#anchor14">RFC3339</a>
</td>
</tr>
<tr>
<td>dateTime</td>
<td><code>string</code></td>
<td><code>date-time</code></td>
<td>As defined by <code>date-time</code> - <a href="http://xml2rfc.ietf.org/public/rfc/html/rfc3339.html#anchor14">RFC3339</a>
</td>
</tr>
</tbody>
</table>

<h3>
<a id="user-content-schema" class="anchor" href="#schema" aria-hidden="true"><span class="octicon octicon-link"></span></a>Schema</h3>

<h4>
<a id="user-content-swagger-object-" class="anchor" href="#swagger-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Swagger Object <a name="user-content-swaggerObject"></a>
</h4>

<p>This is the root document object for the API specification. It combines what previously was the Resource Listing and API Declaration (version 1.2 and earlier) together into one document.</p>

<h5>
<a id="user-content-fixed-fields" class="anchor" href="#fixed-fields" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-swaggerSwagger"></a>swagger</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> Specifies the Swagger Specification version being used. It can be used by the Swagger UI and other clients to interpret the API listing. The value MUST be <code>"2.0"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerInfo"></a>info</td>
<td align="center"><a href="#infoObject">Info Object</a></td>
<td>
<strong>Required.</strong> Provides metadata about the API. The metadata can be used by the clients if needed.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerHost"></a>host</td>
<td align="center"><code>string</code></td>
<td>The host (name or ip) serving the API. This MUST be the host only and does not include the scheme nor sub-paths. It MAY include a port. If the <code>host</code> is not included, the host serving the documentation is to be used (including the port). The <code>host</code> does not support <a href="#pathTemplating">path templating</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerBasePath"></a>basePath</td>
<td align="center"><code>string</code></td>
<td>The base path on which the API is served, which is relative to the <a href="#swaggerHost"><code>host</code></a>. If it is not included, the API is served directly under the <code>host</code>. The value MUST start with a leading slash (<code>/</code>). The <code>basePath</code> does not support <a href="#pathTemplating">path templating</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerSchemes"></a>schemes</td>
<td align="center">[<code>string</code>]</td>
<td>The transfer protocol of the API. Values MUST be from the list: <code>"http"</code>, <code>"https"</code>, <code>"ws"</code>, <code>"wss"</code>. If the <code>schemes</code> is not included, the default scheme to be used is the one used to access the specification.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerConsumes"></a>consumes</td>
<td align="center">[<code>string</code>]</td>
<td>A list of MIME types the APIs can consume. This is global to all APIs but can be overridden on specific API calls. Value MUST be as described under <a href="#mimeTypes">Mime Types</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerProduces"></a>produces</td>
<td align="center">[<code>string</code>]</td>
<td>A list of MIME types the APIs can produce. This is global to all APIs but can be overridden on specific API calls. Value MUST be as described under <a href="#mimeTypes">Mime Types</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerPaths"></a>paths</td>
<td align="center"><a href="#pathsObject">Paths Object</a></td>
<td>
<strong>Required.</strong> The available paths and operations for the API.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerDefinitions"></a>definitions</td>
<td align="center"><a href="#definitionsObject">Definitions Object</a></td>
<td>An object to hold data types produced and consumed by operations.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerParameters"></a>parameters</td>
<td align="center"><a href="#parametersDefinitionsObject">Parameters Definitions Object</a></td>
<td>An object to hold parameters that can be used across operations. This property <em>does not</em> define global parameters for all operations.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerResponses"></a>responses</td>
<td align="center"><a href="#responsesDefinitionsObject">Responses Definitions Object</a></td>
<td>An object to hold responses that can be used across operations. This property <em>does not</em> define global responses for all operations.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerSecurityDefinitions"></a>securityDefinitions</td>
<td align="center"><a href="#securityDefinitionsObject">Security Definitions Object</a></td>
<td>Security scheme definitions that can be used across the specification.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerSecurity"></a>security</td>
<td align="center">[<a href="#securityRequirementObject">Security Requirement Object</a>]</td>
<td>A declaration of which security schemes are applied for the API as a whole. The list of values describes alternative security schemes that can be used (that is, there is a logical OR between the security requirements). Individual operations can override this definition.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerTags"></a>tags</td>
<td align="center">[<a href="#tagObject">Tag Object</a>]</td>
<td>A list of tags used by the specification with additional metadata. The order of the tags can be used to reflect on their order by the parsing tools. Not all tags that are used by the <a href="#operationObject">Operation Object</a> must be declared. The tags that are not declared may be organized randomly or based on the tools' logic. Each tag name in the list MUST be unique.</td>
</tr>
<tr>
<td>
<a name="user-content-swaggerExternalDocs"></a>externalDocs</td>
<td align="center"><a href="#externalDocumentationObject">External Documentation Object</a></td>
<td>Additional external documentation.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example" class="anchor" href="#object-example" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example:</h5>

<p><strong>TODO: add sample</strong></p>

<h4>
<a id="user-content-info-object-" class="anchor" href="#info-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Info Object <a name="user-content-infoObject"></a>
</h4>

<p>The object provides metadata about the API. The metadata can be used by the clients if needed, and can be presented in the Swagger-UI for convenience.</p>

<h5>
<a id="user-content-fixed-fields-1" class="anchor" href="#fixed-fields-1" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-infoTitle"></a>title</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The title of the application.</td>
</tr>
<tr>
<td>
<a name="user-content-infoDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A short description of the application. <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-infoTermsOfService"></a>termsOfService</td>
<td align="center"><code>string</code></td>
<td>The Terms of Service for the API.</td>
</tr>
<tr>
<td>
<a name="user-content-infoContact"></a>contact</td>
<td align="center"><a href="#contactObject">Contact Object</a></td>
<td>The contact information for the exposed API.</td>
</tr>
<tr>
<td>
<a name="user-content-infoLicense"></a>license</td>
<td align="center"><a href="#licenseObject">License Object</a></td>
<td>The license information for the exposed API.</td>
</tr>
<tr>
<td>
<a name="user-content-infoVersion"></a>version</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required</strong> Provides the version of the application API (not to be confused by the specification version).</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-objects" class="anchor" href="#patterned-objects" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Objects</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-operationExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-1" class="anchor" href="#object-example-1" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example:</h5>

<div class="highlight highlight-js"><pre>{
  <span class="pl-s1"><span class="pl-pds">"</span>title<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>Swagger Sample App<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>description<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>This is a sample server Petstore server.<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>termsOfService<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>http://swagger.io/terms/<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>contact<span class="pl-pds">"</span></span><span class="pl-k">:</span> {
    <span class="pl-s1"><span class="pl-pds">"</span>name<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>API Support<span class="pl-pds">"</span></span>,
    <span class="pl-s1"><span class="pl-pds">"</span>url<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>http://www.swagger.io/support<span class="pl-pds">"</span></span>,
    <span class="pl-s1"><span class="pl-pds">"</span>email<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>support@swagger.io<span class="pl-pds">"</span></span>,
  },
  <span class="pl-s1"><span class="pl-pds">"</span>license<span class="pl-pds">"</span></span><span class="pl-k">:</span> {
    <span class="pl-s1"><span class="pl-pds">"</span>name<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>Apache 2.0<span class="pl-pds">"</span></span>,
    <span class="pl-s1"><span class="pl-pds">"</span>url<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>http://www.apache.org/licenses/LICENSE-2.0.html<span class="pl-pds">"</span></span>
  },
  <span class="pl-s1"><span class="pl-pds">"</span>version<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>1.0.1<span class="pl-pds">"</span></span>
 }</pre></div>

<h4>
<a id="user-content-contact-object-" class="anchor" href="#contact-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Contact Object <a name="user-content-contactObject"></a>
</h4>

<p>Contact information for the exposed API.</p>

<h5>
<a id="user-content-fixed-fields-2" class="anchor" href="#fixed-fields-2" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-contactName"></a>name</td>
<td align="center"><code>string</code></td>
<td>The identifying name of the contact person/organization.</td>
</tr>
<tr>
<td>
<a name="user-content-contactUrl"></a>url</td>
<td align="center"><code>string</code></td>
<td>The URL pointing to the contact information. MUST be in the format of a URL.</td>
</tr>
<tr>
<td>
<a name="user-content-contactEmail"></a>email</td>
<td align="center"><code>string</code></td>
<td>The email address of the contact person/organization. MUST be in the format of an email address.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-2" class="anchor" href="#object-example-2" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example:</h5>

<div class="highlight highlight-js"><pre>{
  <span class="pl-s1"><span class="pl-pds">"</span>name<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>API Support<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>url<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>http://www.swagger.io/support<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>email<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>support@swagger.io<span class="pl-pds">"</span></span>,
}</pre></div>

<h4>
<a id="user-content-license-object-" class="anchor" href="#license-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>License Object <a name="user-content-licenseObject"></a>
</h4>

<p>License information for the exposed API.</p>

<h5>
<a id="user-content-fixed-fields-3" class="anchor" href="#fixed-fields-3" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-licenseName"></a>name</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The license name used for the API.</td>
</tr>
<tr>
<td>
<a name="user-content-licenseUrl"></a>url</td>
<td align="center"><code>string</code></td>
<td>A URL to the license used for the API. MUST be in the format of a URL.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-3" class="anchor" href="#object-example-3" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example:</h5>

<div class="highlight highlight-js"><pre>{
  <span class="pl-s1"><span class="pl-pds">"</span>name<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>Apache 2.0<span class="pl-pds">"</span></span>,
  <span class="pl-s1"><span class="pl-pds">"</span>url<span class="pl-pds">"</span></span><span class="pl-k">:</span> <span class="pl-s1"><span class="pl-pds">"</span>http://www.apache.org/licenses/LICENSE-2.0.html<span class="pl-pds">"</span></span>
}</pre></div>

<h4>
<a id="user-content-paths-object-" class="anchor" href="#paths-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Paths Object <a name="user-content-pathsObject"></a>
</h4>

<p>Holds the relative paths to the individual endpoints. The path is appended to the <a href="#swaggerBasePath"><code>basePath</code></a> in order to construct the full URL.
The Paths may be empty, due to <a href="#securityFiltering">ACL constraints</a>.</p>

<h5>
<a id="user-content-patterned-fields" class="anchor" href="#patterned-fields" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-pathsPath"></a>/{path}</td>
<td align="center"><a href="#pathItemObject">Path Item Object</a></td>
<td>A relative path to an individual endpoint. The field name MUST begin with a slash. The path is appended to the <a href="#swaggerBasePath"><code>basePath</code></a> in order to construct the full URL. <a href="#pathTemplating">Path templating</a> is allowed.</td>
</tr>
<tr>
<td>
<a name="user-content-pathsExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-4" class="anchor" href="#object-example-4" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-path-item-object-" class="anchor" href="#path-item-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Path Item Object <a name="user-content-pathItemObject"></a>
</h4>

<p>Describes the operations available on a single path.
A Path Item may be empty, due to <a href="#securityFiltering">ACL constraints</a>. The path itself is still exposed to the documentation viewer but they will not know which operations and parameters are available.</p>

<h5>
<a id="user-content-fixed-fields-4" class="anchor" href="#fixed-fields-4" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-pathItemRef"></a>$ref</td>
<td align="center"><code>string</code></td>
<td>Allows for an external definition of this path item. The referenced structure MUST be in the format of a <a href="#pathItemObject">Path Item Object</a>. If there are conflicts between the referenced definition and this Path Item's definition, the behavior is <em>undefined</em>.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemGet"></a>get</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a GET operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemPut"></a>put</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a PUT operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemPost"></a>post</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a POST operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemDelete"></a>delete</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a DELETE operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemOptions"></a>options</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a OPTIONS operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemHead"></a>head</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a HEAD operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemPatch"></a>patch</td>
<td align="center"><a href="#operationObject">Operation Object</a></td>
<td>A definition of a PATCH operation on this path.</td>
</tr>
<tr>
<td>
<a name="user-content-pathItemParameters"></a>parameters</td>
<td align="center">[<a href="#parameterObject">Parameter Object</a> | <a href="#referenceObject">Reference Object</a>]</td>
<td>A list of parameters that are applicable for all the operations described under this path. These parameters can be overridden at the operation level, but cannot be removed there. The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a <a href="#parameterName">name</a> and <a href="#parameterIn">location</a>. The list can use the <a href="#referenceObject">Reference Object</a> to link to parameters that are defined at the <a href="#swaggerParameters">Swagger Object's parameters</a>. There can be one "body" parameter at most.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-fields-1" class="anchor" href="#patterned-fields-1" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-pathItemExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-5" class="anchor" href="#object-example-5" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-operation-object-" class="anchor" href="#operation-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Operation Object <a name="user-content-operationObject"></a>
</h4>

<p>Describes a single API operation on a path.</p>

<h5>
<a id="user-content-fixed-fields-5" class="anchor" href="#fixed-fields-5" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-operationTags"></a>tags</td>
<td align="center">[<code>string</code>]</td>
<td>A list of tags for API documentation control. Tags can be used for logical grouping of operations by resources or any other qualifier.</td>
</tr>
<tr>
<td>
<a name="user-content-operationSummary"></a>summary</td>
<td align="center"><code>string</code></td>
<td>A short summary of what the operation does. For maximum readability in the swagger-ui, this field SHOULD be less than 120 characters.</td>
</tr>
<tr>
<td>
<a name="user-content-operationDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A verbose explanation of the operation behavior. <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-operationExternalDocs"></a>externalDocs</td>
<td align="center"><a href="#externalDocumentationObject">External Documentation Object</a></td>
<td>Additional external documentation for this operation.</td>
</tr>
<tr>
<td>
<a name="user-content-operationId"></a>operationId</td>
<td align="center"><code>string</code></td>
<td>A friendly name for the operation. The id MUST be unique among all operations described in the API. Tools and libraries MAY use the operation id to uniquely identify an operation.</td>
</tr>
<tr>
<td>
<a name="user-content-operationConsumes"></a>consumes</td>
<td align="center">[<code>string</code>]</td>
<td>A list of MIME types the operation can consume. This overrides the <code>[consumes](#swaggerConsumes)</code> definition at the Swagger Object. An empty value MAY be used to clear the global definition. Value MUST be as described under <a href="#mimeTypes">Mime Types</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-operationProduces"></a>produces</td>
<td align="center">[<code>string</code>]</td>
<td>A list of MIME types the operation can produce. This overrides the <code>[produces](#swaggerProduces)</code> definition at the Swagger Object. An empty value MAY be used to clear the global definition. Value MUST be as described under <a href="#mimeTypes">Mime Types</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-operationParameters"></a>parameters</td>
<td align="center">[<a href="#parameterObject">Parameter Object</a> | <a href="#referenceObject">Reference Object</a>]</td>
<td>A list of parameters that are applicable for this operation. If a parameter is already defined at the <a href="#pathItemParameters">Path Item</a>, the new definition will override it, but can never remove it. The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a <a href="#parameterName">name</a> and <a href="#parameterIn">location</a>. The list can use the <a href="#referenceObject">Reference Object</a> to link to parameters that are defined at the <a href="#swaggerParameters">Swagger Object's parameters</a>. There can be one "body" parameter at most.</td>
</tr>
<tr>
<td>
<a name="user-content-operationResponses"></a>responses</td>
<td align="center"><a href="#responsesObject">Responses Object</a></td>
<td>
<strong>Required.</strong> The list of possible responses as they are returned from executing this operation.</td>
</tr>
<tr>
<td>
<a name="user-content-operationSchemes"></a>schemes</td>
<td align="center">[<code>string</code>]</td>
<td>The transfer protocol for the operation. Values MUST be from the list: <code>"http"</code>, <code>"https"</code>, <code>"ws"</code>, <code>"wss"</code>. The value overrides the Swagger Object <a href="#swaggerSchemes"><code>schemes</code></a> definition.</td>
</tr>
<tr>
<td>
<a name="user-content-operationDeprecated"></a>deprecated</td>
<td align="center"><code>boolean</code></td>
<td>Declares this operation to be deprecated. Usage of the declared operation should be refrained. Default value is <code>false</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-operationSecurity"></a>security</td>
<td align="center">[<a href="#securityRequirementObject">Security Requirement Object</a>]</td>
<td>A declaration of which security schemes are applied for this operation. The list of values describes alternative security schemes that can be used (that is, there is a logical OR between the security requirements). This definition overrides any declared top-level <a href="#swaggerSecurity"><code>security</code></a>. To remove a top-level security declaration, an empty array can be used.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-objects-1" class="anchor" href="#patterned-objects-1" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Objects</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-operationExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-6" class="anchor" href="#object-example-6" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-external-documentation-object-" class="anchor" href="#external-documentation-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>External Documentation Object <a name="user-content-externalDocumentationObject"></a>
</h4>

<p>Allows referencing an external resource for extended documentation.</p>

<h5>
<a id="user-content-fixed-fields-6" class="anchor" href="#fixed-fields-6" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-externalDocDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A short description of the target documentation. <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-externalDocUrl"></a>url</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The URL for the target documentation. Value MUST be in the format of a URL.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-7" class="anchor" href="#object-example-7" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-parameter-object-" class="anchor" href="#parameter-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Parameter Object <a name="user-content-parameterObject"></a>
</h4>

<p>Describes a single operation parameter.</p>

<p>A unique parameter is defined by a combination of a <a href="#parameterName">name</a> and <a href="#parameterIn">location</a>.</p>

<p>There are five possible parameter types.</p>

<ul class="task-list">
<li>Path - Used together with <a href="#pathTemplating">Path Templating</a>, where the parameter value is actually part of the operation's URL. This does not include the host or base path of the API. For example, in <code>/items/{itemId}</code>, the path parameter is <code>itemId</code>.</li>
<li>Query - Parameters that are appended to the URL. For example, in <code>/items?id=###</code>, the query parameter is <code>id</code>.</li>
<li>Header - Custom headers that are expected as part of the request.</li>
<li>Body - The payload that's appended to the HTTP request. Since there can only be one payload, there can only be <em>one</em> body parameter. The name of the body parameter has no effect on the parameter itself and is used for documentation purposes only. Since Form parameters are also in the payload, body and form parameters cannot exist together for the same operation.</li>
<li>Form - Used to describe the payload of an HTTP request when either <code>application/x-www-form-urlencoded</code> or <code>multipart/form-data</code> are used as the content type of the request (in Swagger's definition, the <a href="#operationConsumes"><code>consumes</code></a> property of an operation). This is the only parameter type that can be used to send files, thus supporting the <code>file</code> type. Since form parameters are sent in the payload, they cannot be declared together with a body parameter for the same operation. Form parameters have a different format based on the content-type used (for further details, consult <a href="http://www.w3.org/TR/html401/interact/forms.html#h-17.13.4">http://www.w3.org/TR/html401/interact/forms.html#h-17.13.4</a>):

<ul class="task-list">
<li>
<code>application/x-www-form-urlencoded</code> - Similar to the format of Query parameters but as a payload. For example, <code>foo=1&amp;bar=swagger</code> - both <code>foo</code> and <code>bar</code> are form parameters. This is normally used for simple parameters that are being transferred.</li>
<li>
<code>multipart/form-data</code> - each parameter takes a section in the payload with an internal header. For example, for the header <code>Content-Disposition: form-data; name="submit-name"</code> the name of the parameter is <code>submit-name</code>. This type of form parameters is more commonly used for file transfers.</li>
</ul>
</li>
</ul>

<h5>
<a id="user-content-fixed-fields-7" class="anchor" href="#fixed-fields-7" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-parameterName"></a>name</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The name of the parameter. Parameter names are <em>case sensitive</em>. <ul class="task-list">
<li>If <a href="#parameterIn"><code>in</code></a> is <code>"path"</code>, the <code>name</code> field MUST correspond to the associated path segment from the <a href="#pathsPath">path</a> field in the <a href="#pathsObject">Paths Object</a>. See <a href="#pathTemplating">Path Templating</a> for further information.</li>
<li>For all other cases, the <code>name</code> corresponds to the parameter name used based on the <a href="#parameterIn"><code>in</code></a> property.</li>
</ul>
</td>
</tr>
<tr>
<td>
<a name="user-content-parameterIn"></a>in</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The location of the parameter. Possible values are "query", "header", "path", "formData" or "body".</td>
</tr>
<tr>
<td>
<a name="user-content-parameterDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A brief description of the parameter. This could contain examples of use.  <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterRequired"></a>required</td>
<td align="center"><code>boolean</code></td>
<td>Determines whether this parameter is mandatory. If the parameter is <a href="#parameterIn"><code>in</code></a> "path", this property is <strong>required</strong> and its value MUST be <code>true</code>. Otherwise, the property MAY be included and its default value is <code>false</code>.</td>
</tr>
</tbody>
</table>

<p>If <a href="#parameterIn"><code>in</code></a> is <code>"body"</code>:</p>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-parameterSchema"></a>schema</td>
<td align="center"><a href="#schemaObject">Schema Object</a></td>
<td>
<strong>Required.</strong> The schema defining the type used for the body parameter.</td>
</tr>
</tbody>
</table>

<p>If <a href="#parameterIn"><code>in</code></a> is any value other than <code>"body"</code>:</p>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-parameterType"></a>type</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The type of the parameter. Since the parameter is not located at the request body, it is limited to simple types (that is, not an object). The value MUST be one of <code>"string"</code>, <code>"number"</code>, <code>"integer"</code>, <code>"boolean"</code>, <code>"array"</code> or <code>"file"</code>. If <code>type</code> is <code>"file"</code>, the <a href="#operationConsumes"><code>consumes</code></a> MUST be either <code>"multipart/form-data"</code> or <code>" application/x-www-form-urlencoded"</code> and the parameter MUST be <a href="#parameterIn"><code>in</code></a> <code>"formData"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterFormat"></a>format</td>
<td align="center"><code>string</code></td>
<td>The extending format for the previously mentioned <a href="#parameterType"><code>type</code></a>. See <a href="#dataTypeFormat">Data Type Formats</a> for further details.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterItems"></a>items</td>
<td align="center"><a href="#itemsObject">Items Object</a></td>
<td>
<strong>Required if <a href="#parameterType"><code>type</code></a> is "array".</strong> Describes the type of items in the array.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterCollectionFormat"></a>collectionFormat</td>
<td align="center"><code>string</code></td>
<td>Determines the format of the array if type array is used. Possible values are: <ul class="task-list">
<li>
<code>csv</code> - comma separated values <code>foo,bar</code>. </li>
<li>
<code>ssv</code> - space separated values <code>foo bar</code>. </li>
<li>
<code>tsv</code> - tab separated values <code>foo\tbar</code>. </li>
<li>
<code>pipes</code> - pipe separated values <code>foo|bar</code>. </li>
<li>
<code>multi</code> - corresponds to multiple parameter instances instead of multiple values for a single instance <code>foo=bar&amp;foo=baz</code>. This is valid only for parameters <a href="#parameterIn"><code>in</code></a> "query" or "formData". </li>
</ul> Default value is <code>csv</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterDefault"></a>default</td>
<td align="center">*</td>
<td>Sets a default value to the parameter. The type of the value depends on the defined <a href="#parameterType"><code>type</code></a>. See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor101">http://json-schema.org/latest/json-schema-validation.html#anchor101</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMaximum"></a>maximum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterExclusiveMaximum"></a>exclusiveMaximum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMinimum"></a>minimum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterExclusiveMinimum"></a>exclusiveMinimum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMaxLength"></a>maxLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor26">http://json-schema.org/latest/json-schema-validation.html#anchor26</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMinLength"></a>minLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor29">http://json-schema.org/latest/json-schema-validation.html#anchor29</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterPattern"></a>pattern</td>
<td align="center"><code>string</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor33">http://json-schema.org/latest/json-schema-validation.html#anchor33</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMaxItems"></a>maxItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor42">http://json-schema.org/latest/json-schema-validation.html#anchor42</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMinItems"></a>minItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor45">http://json-schema.org/latest/json-schema-validation.html#anchor45</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterUniqueItems"></a>uniqueItems</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor49">http://json-schema.org/latest/json-schema-validation.html#anchor49</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterEnum"></a>enum</td>
<td align="center">[*]</td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor76">http://json-schema.org/latest/json-schema-validation.html#anchor76</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterMultipleOf"></a>multipleOf</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor14">http://json-schema.org/latest/json-schema-validation.html#anchor14</a>.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-fields-2" class="anchor" href="#patterned-fields-2" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-parameterExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-8" class="anchor" href="#object-example-8" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-items-object-" class="anchor" href="#items-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Items Object <a name="user-content-itemsObject"></a>
</h4>

<p>An limited subset of JSON-Schema's items object. It is used by parameter definitions that are not located <a href="#parameterIn"><code>in</code></a> <code>"body"</code>.</p>

<h5>
<a id="user-content-fixed-fields-8" class="anchor" href="#fixed-fields-8" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-itemsType"></a>type</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The internal type of the array. The value MUST be one of <code>"string"</code>, <code>"number"</code>, <code>"integer"</code>, <code>"boolean"</code>, or <code>"array"</code>. Files and models are not allowed.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsFormat"></a>format</td>
<td align="center"><code>string</code></td>
<td>The extending format for the previously mentioned <a href="#parameterType"><code>type</code></a>. See <a href="#dataTypeFormat">Data Type Formats</a> for further details.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsItems"></a>items</td>
<td align="center"><a href="#itemsObject">Items Object</a></td>
<td>
<strong>Required if <a href="#itemsType"><code>type</code></a> is "array".</strong> Describes the type of items in the array.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsCollectionFormat"></a>collectionFormat</td>
<td align="center"><code>string</code></td>
<td>Determines the format of the array if type array is used. Possible values are: <ul class="task-list">
<li>
<code>csv</code> - comma separated values <code>foo,bar</code>. </li>
<li>
<code>ssv</code> - space separated values <code>foo bar</code>. </li>
<li>
<code>tsv</code> - tab separated values <code>foo\tbar</code>. </li>
<li>
<code>pipes</code> - pipe separated values <code>foo|bar</code>. </li>
</ul> Default value is <code>csv</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsDefault"></a>default</td>
<td align="center">*</td>
<td>Sets a default value to the data type. The type of the value depends on the defined <a href="#itemsType"><code>type</code></a>. See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor101">http://json-schema.org/latest/json-schema-validation.html#anchor101</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMaximum"></a>maximum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMaximum"></a>exclusiveMaximum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMinimum"></a>minimum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsExclusiveMinimum"></a>exclusiveMinimum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMaxLength"></a>maxLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor26">http://json-schema.org/latest/json-schema-validation.html#anchor26</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMinLength"></a>minLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor29">http://json-schema.org/latest/json-schema-validation.html#anchor29</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsPattern"></a>pattern</td>
<td align="center"><code>string</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor33">http://json-schema.org/latest/json-schema-validation.html#anchor33</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMaxItems"></a>maxItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor42">http://json-schema.org/latest/json-schema-validation.html#anchor42</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMinItems"></a>minItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor45">http://json-schema.org/latest/json-schema-validation.html#anchor45</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsUniqueItems"></a>uniqueItems</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor49">http://json-schema.org/latest/json-schema-validation.html#anchor49</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsEnum"></a>enum</td>
<td align="center">[*]</td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor76">http://json-schema.org/latest/json-schema-validation.html#anchor76</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-itemsMultipleOf"></a>multipleOf</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor14">http://json-schema.org/latest/json-schema-validation.html#anchor14</a>.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-9" class="anchor" href="#object-example-9" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-responses-object-" class="anchor" href="#responses-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Responses Object <a name="user-content-responsesObject"></a>
</h4>

<p>A container for the expected responses of an operation. The container maps a HTTP response code to the expected response. It is not expected from the documentation to necessarily cover all possible HTTP response codes, since they may not be known in advance. However, it is expected from the documentation to cover a successful operation response and any known errors.</p>

<p>The <code>default</code> can be used a default response object for all HTTP codes that are not covered individually by the specification.</p>

<p>The <code>Responses Object</code> MUST contain at least one response code, and it SHOULD be the response for a successful operation call.</p>

<h5>
<a id="user-content-fixed-fields-9" class="anchor" href="#fixed-fields-9" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-responsesDefault"></a>default</td>
<td align="center">
<a href="#responseObject">Response Object</a> | <a href="#referenceObject">Reference Object</a>
</td>
<td>The documentation of responses other than the ones declared for specific HTTP response codes. It can be used to cover undeclared responses. <a href="#referenceObject">Reference Object</a> can be used to link to a response that is defined at the <a href="#swaggerResponses">Swagger Object's responses</a> section.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-fields-3" class="anchor" href="#patterned-fields-3" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-responsesCode"></a>{<a href="#httpCodes">HTTP Status Code</a>}</td>
<td align="center">
<a href="#responseObject">Response Object</a> | <a href="#referenceObject">Reference Object</a>
</td>
<td>Any <a href="#httpCodes">HTTP status code</a> can be used as the property name (one property per HTTP status code). Describes the expected response for that HTTP status code.  <a href="#referenceObject">Reference Object</a> can be used to link to a response that is defined at the <a href="#swaggerResponses">Swagger Object's responses</a> section.</td>
</tr>
<tr>
<td>
<a name="user-content-parameterExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-10" class="anchor" href="#object-example-10" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-response-object-" class="anchor" href="#response-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Response Object <a name="user-content-responseObject"></a>
</h4>

<p>Describes a single response from an API Operation.</p>

<h5>
<a id="user-content-fixed-fields-10" class="anchor" href="#fixed-fields-10" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-responseDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> A short description of the response. <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-responseSchema"></a>schema</td>
<td align="center"><a href="#schemaObject">Schema Object</a></td>
<td>A definition of the response structure. It can be a primitive, an array or an object. If this field does not exist, it means no content is returned as part of the response. As an extension to the <a href="#schemaObject">Schema Object</a>, its root <code>type</code> value may also be <code>"file"</code>. This SHOULD be accompanied by a relevant <code>produces</code> mime-type.</td>
</tr>
<tr>
<td>
<a name="user-content-responseHeaders"></a>headers</td>
<td align="center"><a href="#headersObject">Headers Object</a></td>
<td>A list of headers that are sent with the response.</td>
</tr>
<tr>
<td>
<a name="user-content-responseExamples"></a> examples</td>
<td align="center"><a href="#exampleObject">Example Object</a></td>
<td>An example of the response message.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-11" class="anchor" href="#object-example-11" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-headers-object-" class="anchor" href="#headers-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Headers Object <a name="user-content-headersObject"></a>
</h4>

<p>Lists the headers that can be sent as part of a response.</p>

<h5>
<a id="user-content-patterned-fields-4" class="anchor" href="#patterned-fields-4" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-headersName"></a>{name}</td>
<td align="center"><a href="#headerObject">Header Object</a></td>
<td>The name of the property corresponds to the name of the header. The value describes the type of the header.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-12" class="anchor" href="#object-example-12" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-example-object-" class="anchor" href="#example-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Example Object <a name="user-content-exampleObject"></a>
</h4>

<p>Allows sharing examples for operation responses.</p>

<h5>
<a id="user-content-patterned-fields-5" class="anchor" href="#patterned-fields-5" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-exampleMimeType"></a>{<a href="#mimeTypes">mime type</a>}</td>
<td align="center">Any</td>
<td>The name of the property MUST be one of the Operation <code>produces</code> values (either implicit or inherited). The value SHOULD be an example of what such a response would look like.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-13" class="anchor" href="#object-example-13" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-header-object-" class="anchor" href="#header-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Header Object <a name="user-content-headerObject"></a>
</h4>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-headerDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A short description of the header.</td>
</tr>
<tr>
<td>
<a name="user-content-headerType"></a>type</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The type of the object. The value MUST be one of <code>"string"</code>, <code>"number"</code>, <code>"integer"</code>, <code>"boolean"</code>, or <code>"array"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerFormat"></a>format</td>
<td align="center"><code>string</code></td>
<td>The extending format for the previously mentioned <a href="#stType"><code>type</code></a>. See <a href="#dataTypeFormat">Data Type Formats</a> for further details.</td>
</tr>
<tr>
<td>
<a name="user-content-headerItems"></a>items</td>
<td align="center"><a href="#itemsObject">Items Object</a></td>
<td>
<strong>Required if <a href="#stType"><code>type</code></a> is "array".</strong> Describes the type of items in the array.</td>
</tr>
<tr>
<td>
<a name="user-content-headerCollectionFormat"></a>collectionFormat</td>
<td align="center"><code>string</code></td>
<td>Determines the format of the array if type array is used. Possible values are: <ul class="task-list">
<li>
<code>csv</code> - comma separated values <code>foo,bar</code>. </li>
<li>
<code>ssv</code> - space separated values <code>foo bar</code>. </li>
<li>
<code>tsv</code> - tab separated values <code>foo\tbar</code>. </li>
<li>
<code>pipes</code> - pipe separated values <code>foo|bar</code>. </li>
</ul> Default value is <code>csv</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerDefault"></a>default</td>
<td align="center">*</td>
<td>Sets a default value to the data type. The type of the value depends on the defined <a href="#stType"><code>type</code></a>. See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor101">http://json-schema.org/latest/json-schema-validation.html#anchor101</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMaximum"></a>maximum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMaximum"></a>exclusiveMaximum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor17">http://json-schema.org/latest/json-schema-validation.html#anchor17</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMinimum"></a>minimum</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerExclusiveMinimum"></a>exclusiveMinimum</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor21">http://json-schema.org/latest/json-schema-validation.html#anchor21</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMaxLength"></a>maxLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor26">http://json-schema.org/latest/json-schema-validation.html#anchor26</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMinLength"></a>minLength</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor29">http://json-schema.org/latest/json-schema-validation.html#anchor29</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerPattern"></a>pattern</td>
<td align="center"><code>string</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor33">http://json-schema.org/latest/json-schema-validation.html#anchor33</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMaxItems"></a>maxItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor42">http://json-schema.org/latest/json-schema-validation.html#anchor42</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMinItems"></a>minItems</td>
<td align="center"><code>integer</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor45">http://json-schema.org/latest/json-schema-validation.html#anchor45</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerUniqueItems"></a>uniqueItems</td>
<td align="center"><code>boolean</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor49">http://json-schema.org/latest/json-schema-validation.html#anchor49</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerEnum"></a>enum</td>
<td align="center">[*]</td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor76">http://json-schema.org/latest/json-schema-validation.html#anchor76</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-headerMultipleOf"></a>multipleOf</td>
<td align="center"><code>number</code></td>
<td>See <a href="http://json-schema.org/latest/json-schema-validation.html#anchor14">http://json-schema.org/latest/json-schema-validation.html#anchor14</a>.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-14" class="anchor" href="#object-example-14" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-tag-object-" class="anchor" href="#tag-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Tag Object <a name="user-content-tagObject"></a>
</h4>

<p>Allows adding meta data to a single tag that is used by the <a href="#operationObject">Operation Object</a>. It is not mandatory to have a Tag Object per tag used there.</p>

<h5>
<a id="user-content-fixed-fields-11" class="anchor" href="#fixed-fields-11" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-tagName"></a>name</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The name of the tag.</td>
</tr>
<tr>
<td>
<a name="user-content-tagDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>A short description for the tag. <a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation.</td>
</tr>
<tr>
<td>
<a name="user-content-tagExternalDocs"></a>externalDocs</td>
<td align="center"><a href="#externalDocumentationObject">External Documentation Object</a></td>
<td>Additional external documentation for this tag.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-fields-6" class="anchor" href="#patterned-fields-6" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-tagExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-15" class="anchor" href="#object-example-15" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-reference-object-" class="anchor" href="#reference-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Reference Object <a name="user-content-referenceObject"></a>
</h4>

<p>A simple object to allow referencing other definitions in the specification. It can be used to reference parameters and responses that are defined at the top level for reuse.</p>

<h5>
<a id="user-content-fixed-fields-12" class="anchor" href="#fixed-fields-12" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-referenceRef"></a>$ref</td>
<td align="center"><code>string</code></td>
<td>
<strong>Required.</strong> The reference string.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-16" class="anchor" href="#object-example-16" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-schema-object-" class="anchor" href="#schema-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Schema Object <a name="user-content-schemaObject"></a>
</h4>

<p>The Schema Object allows the definition of input and output data types. These types can be objects, but also primitives and arrays. This object is based on the <a href="http://json-schema.org/">JSON Schema Specification Draft 4</a> and uses a predefined subset of it. On top of this subset, there are extensions provided by this specification to allow for more complete documentation.</p>

<p>The following properties are taken directly from the JSON Schema definition and follow the same specifications:</p>

<ul class="task-list">
<li>$ref</li>
<li>format (See <a href="#dataTypeFormat">Data Type Formats</a> for further details)</li>
<li>title</li>
<li>description (<a href="https://help.github.com/articles/github-flavored-markdown">GFM syntax</a> can be used for rich text representation)</li>
<li>default</li>
<li>multipleOf</li>
<li>maximum</li>
<li>exclusiveMaximum</li>
<li>minimum</li>
<li>exclusiveMinimum</li>
<li>maxLength</li>
<li>minLength</li>
<li>pattern</li>
<li>maxItems</li>
<li>minItems</li>
<li>uniqueItems</li>
<li>maxProperties</li>
<li>minProperties</li>
<li>required</li>
<li>enum</li>
<li>type</li>
</ul>

<p>The following properties are taken from the JSON Schema definition but their definitions were adjusted to the Swagger Specification. Their definition is the same as the one from JSON Schema, only where the original definition references the JSON Schema definition, the <a href="#schemaObject">Schema Object</a> definition is used instead.</p>

<ul class="task-list">
<li>items</li>
<li>allOf</li>
<li>properties</li>
</ul>

<p>Other than the JSON Schema subset fields, the following fields may be used for further schema documentation.</p>

<h5>
<a id="user-content-fixed-fields-13" class="anchor" href="#fixed-fields-13" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-schemaDiscriminator"></a>discriminator</td>
<td align="center"><code>string</code></td>
<td>Adds support for polymorphism. The discriminator is the schema property name that is used to differentiate between other schemas that inherit this schema. The property name used MUST be defined at this schema and it MUST be in the <code>required</code> property list. When used, the value MUST be the name of this schema or any schema that inherits it.</td>
</tr>
<tr>
<td>
<a name="user-content-schemaReadOnly"></a>readOnly</td>
<td align="center"><code>boolean</code></td>
<td>Relevant only for Schema <code>"properties"</code> definitions. Declares the property as "read only". This means that it MAY be sent as part of a response but MUST NOT be sent as part of the request. Properties marked as <code>readOnly</code> being <code>true</code> SHOULD NOT be in the <code>required</code> list of the defined schema. Default value is <code>false</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-schemaXml"></a>xml</td>
<td align="center"><a href="#xmlObject">XML Object</a></td>
<td>This MAY be used only on properties schemas. It has no effect on root schemas. Adds Additional metadata to describe the XML representation format of this property.</td>
</tr>
<tr>
<td>
<a name="user-content-schemaExternalDocs"></a>externalDocs</td>
<td align="center"><a href="#externalDocumentationObject">External Documentation Object</a></td>
<td>Additional external documentation for this schema.</td>
</tr>
<tr>
<td>
<a name="user-content-schemaExample"></a>example</td>
<td align="center">Object</td>
<td>A free-form property to include a an example of an instance for this schema.</td>
</tr>
</tbody>
</table>

<p><strong>TODO: Add explanation about composition and inheritance in the new spec.</strong></p>

<h5>
<a id="user-content-object-example-17" class="anchor" href="#object-example-17" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-xml-object-" class="anchor" href="#xml-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>XML Object <a name="user-content-xmlObject"></a>
</h4>

<p>A metadata object that allows for more fine-tuned XML model definitions.</p>

<h5>
<a id="user-content-fixed-fields-14" class="anchor" href="#fixed-fields-14" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-xmlName"></a>name</td>
<td align="center"><code>string</code></td>
<td>Replaces the name of the element/attribute used for the described schema property.</td>
</tr>
<tr>
<td>
<a name="user-content-xmlNamespace"></a>namespace</td>
<td align="center"><code>string</code></td>
<td>The URL of the namespace definition. Value SHOULD be in the form of a URL.</td>
</tr>
<tr>
<td>
<a name="user-content-xmlPrefix"></a>prefix</td>
<td align="center"><code>string</code></td>
<td>The prefix to be used for the <a href="#xmlName">name</a>.</td>
</tr>
<tr>
<td>
<a name="user-content-xmlAttribute"></a>attribute</td>
<td align="center"><code>boolean</code></td>
<td>Declares whether the property definition translates to an attribute instead of an element. Default value is <code>false</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-xmlWrapped"></a>wrapped</td>
<td align="center"><code>boolean</code></td>
<td>MAY be used only for an array definition. Signifies whether the array is wrapped (for example, <code>&lt;books&gt;&lt;book/&gt;&lt;book/&gt;&lt;/books&gt;</code>) or unwrapped (<code>&lt;book/&gt;&lt;book/&gt;</code>). Default value is <code>false</code>.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-18" class="anchor" href="#object-example-18" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-definitions-object-" class="anchor" href="#definitions-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Definitions Object <a name="user-content-definitionsObject"></a>
</h4>

<p>An object to hold data types that can be consumed and produced by operations. These data types can be primitives, arrays or models.</p>

<h5>
<a id="user-content-patterned-fields-7" class="anchor" href="#patterned-fields-7" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-definitionsName"></a>{name}</td>
<td align="center"><a href="#schemaObject">Schema Object</a></td>
<td>A single definition, mapping a "name" to the schema it defines.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-19" class="anchor" href="#object-example-19" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-parameters-definitions-object-" class="anchor" href="#parameters-definitions-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Parameters Definitions Object <a name="user-content-parametersDefinitionsObject"></a>
</h4>

<p>An object to hold parameters to be reused across operations. Parameter definitions can be referenced to the ones defined here.</p>

<p>This does <em>not</em> define global operation parameters.</p>

<h5>
<a id="user-content-patterned-fields-8" class="anchor" href="#patterned-fields-8" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-pdName"></a>{name}</td>
<td align="center"><a href="#parameterObject">Parameter Object</a></td>
<td>A single parameter definition, mapping a "name" to the parameter it defines.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-20" class="anchor" href="#object-example-20" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-responses-definitions-object-" class="anchor" href="#responses-definitions-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Responses Definitions Object <a name="user-content-responsesDefinitionsObject"></a>
</h4>

<p>An object to hold responses to be reused across operations. Response definitions can be referenced to the ones defined here.</p>

<p>This does <em>not</em> define global operation responses.</p>

<h5>
<a id="user-content-patterned-fields-9" class="anchor" href="#patterned-fields-9" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-rdName"></a>{name}</td>
<td align="center"><a href="#responseObject">Response Object</a></td>
<td>A single response definition, mapping a "name" to the response it defines.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-21" class="anchor" href="#object-example-21" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-security-definitions-object-" class="anchor" href="#security-definitions-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Security Definitions Object <a name="user-content-securityDefinitionsObject"></a>
</h4>

<p>A declaration of the security schemes available to be used in the specification. This does not enforce the security schemes on the operations and only serves to provide the relevant details for each scheme.</p>

<h5>
<a id="user-content-patterned-fields-10" class="anchor" href="#patterned-fields-10" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-sdName"></a>{name}</td>
<td align="center"><a href="#securitySchemeObject">Security Scheme Object</a></td>
<td>A single security scheme definition, mapping a "name" to the scheme it defines.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-22" class="anchor" href="#object-example-22" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-security-scheme-object-" class="anchor" href="#security-scheme-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Security Scheme Object <a name="user-content-securitySchemeObject"></a>
</h4>

<p>Allows the definition of a security scheme that can be used by the operations. Supported schemes are basic authentication, an API key (either as a header or as a query parameter) and OAuth2's common flows (implicit, password, application and access code).</p>

<h5>
<a id="user-content-fixed-fields-15" class="anchor" href="#fixed-fields-15" aria-hidden="true"><span class="octicon octicon-link"></span></a>Fixed Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Validity</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-securitySchemeType"></a>type</td>
<td align="center"><code>string</code></td>
<td>Any</td>
<td>
<strong>Required.</strong> The type of the security scheme. Valid values are <code>"basic"</code>, <code>"apiKey"</code> or <code>"oauth2"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeDescription"></a>description</td>
<td align="center"><code>string</code></td>
<td>Any</td>
<td>A short description for security scheme.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeName"></a>name</td>
<td align="center"><code>string</code></td>
<td><code>apiKey</code></td>
<td>
<strong>Required.</strong> The name of the header or query parameter to be used.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeIn"></a>in</td>
<td align="center"><code>string</code></td>
<td><code>apiKey</code></td>
<td>
<strong>Required</strong> The location of the API key. Valid values are <code>"query"</code> or <code>"header"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeFlow"></a>flow</td>
<td align="center"><code>string</code></td>
<td><code>oauth2</code></td>
<td>
<strong>Required.</strong> The flow used by the OAuth2 security scheme. Valid values are <code>"implicit"</code>, <code>"password"</code>, <code>"application"</code> or <code>"accessCode"</code>.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeAuthorizationUrl"></a>authorizationUrl</td>
<td align="center"><code>string</code></td>
<td>
<code>oauth2</code> (<code>"implicit"</code>, <code>"accessCode"</code>)</td>
<td>
<strong>Required.</strong> The authorization URL to be used for this flow. This SHOULD be in the form of a URL.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeTokenUrl"></a>tokenUrl</td>
<td align="center"><code>string</code></td>
<td>
<code>oauth2</code> (<code>"password"</code>, <code>"application"</code>, <code>"accessCode"</code>)</td>
<td>
<strong>Required.</strong> The token URL to be used for this flow. This SHOULD be in the form of a URL.</td>
</tr>
<tr>
<td>
<a name="user-content-securitySchemeScopes"></a>scopes</td>
<td align="center"><a href="#scopesObject">Scopes Object</a></td>
<td><code>oauth2</code></td>
<td>
<strong>Required.</strong> The available scopes for the OAuth2 security scheme.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-patterned-fields-11" class="anchor" href="#patterned-fields-11" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Name</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-securitySchemeExtensions"></a>^x-</td>
<td align="center">Any</td>
<td>Allows extensions to the Swagger Schema. The field name MUST begin with <code>x-</code>, for example, <code>x-internal-id</code>. The value can be <code>null</code>, a primitive, an array or an object. See <a href="#vendorExtensions">Vendor Extensions</a> for further details.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-23" class="anchor" href="#object-example-23" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-scopes-object-" class="anchor" href="#scopes-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Scopes Object <a name="user-content-scopesObject"></a>
</h4>

<p>Lists the available scopes for an OAuth2 security scheme.</p>

<h5>
<a id="user-content-patterned-fields-12" class="anchor" href="#patterned-fields-12" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-scopesName"></a>{name}</td>
<td align="center"><code>string</code></td>
<td>Maps between a name of a scope to a short description of it (as the value of the property).</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-24" class="anchor" href="#object-example-24" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h4>
<a id="user-content-security-requirement-object-" class="anchor" href="#security-requirement-object-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Security Requirement Object <a name="user-content-securityRequirementObject"></a>
</h4>

<p>Lists the required security schemes to execute this operation. The object can have multiple security schemes declared in it which are all required (that is, there is a logical AND between the schemes).</p>

<p>The name used for each property MUST correspond to a security scheme declared in the <a href="#securityDefinitionsObject">Security Definitions</a>.</p>

<h5>
<a id="user-content-patterned-fields-13" class="anchor" href="#patterned-fields-13" aria-hidden="true"><span class="octicon octicon-link"></span></a>Patterned Fields</h5>

<table>
<thead>
<tr>
<th>Field Pattern</th>
<th align="center">Type</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<a name="user-content-securityRequirementsName"></a>{name}</td>
<td align="center">[<code>string</code>]</td>
<td>Each name must correspond to a security scheme which is declared in the <a href="#securityDefinitions">Security Definitions</a>. If the security scheme is of type <code>"oauth2"</code>, then the value is a list of scope names required for the execution. For other security scheme types, the array MUST be empty.</td>
</tr>
</tbody>
</table>

<h5>
<a id="user-content-object-example-25" class="anchor" href="#object-example-25" aria-hidden="true"><span class="octicon octicon-link"></span></a>Object Example</h5>

<p><strong>TODO: add example.</strong></p>

<h3>
<a id="user-content-specification-extensions-" class="anchor" href="#specification-extensions-" aria-hidden="true"><span class="octicon octicon-link"></span></a>Specification Extensions <a name="user-content-vendorExtensions"></a>
</h3>

<p>While the Swagger Specification tries to accommodate most use cases, additional data can be added to extend the specification at certain points.</p>

<p>The extensions properties are always prefixed by <code>"x-"</code> and can have any valid JSON format value.</p>

<p>The extensions may or may not be supported by the available tooling, but those may be extended as well to add requested support (if tools are internal or open-sourced).</p>

<h3>
<a id="user-content-security-filtering" class="anchor" href="#security-filtering" aria-hidden="true"><span class="octicon octicon-link"></span></a>Security Filtering</h3>

<p>Some objects in the Swagger specification may be declared and remain empty, or completely be removed, even though they are inherently the core of the API documentation. </p>

<p>The reasoning behind it is to allow an additional layer of access control over the documentation itself. While not part of the specification itself, certain libraries may choose to allow access to parts of the documentation based on some form of authentication/authorization.</p>

<p>Two examples for this:
1. The <a href="#pathsObject">Paths Object</a> may be empty. It may be counterintuitive, but this may tell the viewer that they got to the right place, but can't access any documentation. They'd still have access to the <a href="#infoObject">Info Object</a> which may contain additional information regarding authentication.
2. The <a href="#pathItemObject">Path Item Object</a> may be empty. In this case, the viewer will be aware that the path exists, but will not be able to see any of its operations or parameters. This is different than hiding the path itself from the <a href="#pathsObject">Paths Object</a> so the user will not be aware of its existence. This allows the documentation provider a finer control over what the viewer can see.</p>
</article>
  </div>

  </div>
</div>
