#include "foo.ice"

module consumer {

    interface TestClassConsumer {
        void accept(java2slice::testinlib::TestClassIce t);
    };
};
