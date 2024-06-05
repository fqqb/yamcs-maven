==================
Yamcs Maven Plugin
==================

.. raw:: latex

    \chapter{About Yamcs Maven Plugin}

This is a Maven plugin for developing a Yamcs application.

Yamcs is a Java-based open source mission control framework. Its functionalities can be extended with your own custom code.

.. rubric:: Goals

.. list-table::
    :widths: 40 60
    :header-rows: 1

    * - Goal
      - Description
    * - :doc:`goals/run`
      - Run Yamcs as part of a Maven build.
    * - :doc:`goals/debug`
      - Run Yamcs in debug mode as part of a Maven build.
    * - :doc:`goals/bundle`
      - Bundle a Yamcs application into a single archive file.
    * - :doc:`goals/run-tool`
      - Run a Yamcs-related tool as part of a Maven build.
    * - :doc:`goals/detect`
      - Detect metadata for Yamcs plugins.
    * - :doc:`goals/protoc`
      - Generated Java sources from proto files.


.. rubric:: Usage

This plugin expects to find Yamcs configuration in ``${project.basedir}/src/main/yamcs`` in subfolders ``etc`` and ``mdb``.

In the pom.xml add dependencies to the desired Yamcs modules. At least a dependency to yamcs-core is required. yamcs-web is another common dependency that makes Yamcs host a prebuilt copy of the Yamcs web interface:

.. code-block:: xml

    <project>
      ...
      <packaging>jar</packaging>
    
      <properties>
        <yamcsVersion>{{ YAMCS_VERSION }}</yamcsVersion>
      </properties>
    
      <dependencies>
        <dependency>
          <groupId>org.yamcs</groupId>
          <artifactId>yamcs-core</artifactId>
          <version>${yamcsVersion}</version>
        </dependency>
        <dependency>
          <groupId>org.yamcs</groupId>
          <artifactId>yamcs-web</artifactId>
          <version>${yamcsVersion}</version>
        </dependency>
        ...
      </dependencies>
    
      <build>
        <plugins>
          <plugin>
            <groupId>org.yamcs</groupId>
            <artifactId>yamcs-maven-plugin</artifactId>
            <version>{{ YAMCS_PLUGIN_VERSION }}</version>
          </plugin>
        </plugins>
      </build>
    
    </project>

To run a Yamcs application:

.. code-block::

    mvn yamcs:run


.. rubric:: Examples

* :doc:`examples/plugin`
* :doc:`examples/packaging`
* :doc:`examples/multi`

.. toctree::
    :hidden:
    :maxdepth: 1
    :titlesonly:

    goals/index
    examples/index
